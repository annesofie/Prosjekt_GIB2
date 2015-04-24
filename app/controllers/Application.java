package controllers;

import models.*;

import play.*;
import play.data.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import java.util.*;


public class Application extends Controller {

    public static class Login {

        public String email;
        public String password;

        public String validate() {
            if (User.authenticate(email, password) == null) {
                return "Invalid user or password";
            }
            return null;
        }

    }

    //Kontroller-metoden som sender data inn til home html-filen, som er startvinduet
    public static Result home() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();


        for(Vare vare:alleVarer){

            if(vare.kategori.equals("elektro")){
                elektro.add(vare);
            }
            if(vare.kategori.equals("fritid")){
                fritid.add(vare);
            }
            if(vare.kategori.equals("jernvare")){
                jernvare.add(vare);
            }
            if(vare.kategori.equals("multimedia")){
                multimedia.add(vare);
            }
            if(vare.kategori.equals("hjem")){
                hjem.add(vare);
            }

        }
        return ok(home.render(request().username(),alleVarer, elektro, fritid, hjem,
                jernvare, multimedia, play.data.Form.form(Login.class), play.data.Form.form(User.class)));
    }

    //Kontroller metoden som sender data inn til index html-filen, her sendes det samme inn som i home pluss en handleliste
    @Security.Authenticated(Secured.class)
    public static Result index(){


        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        User user = User.find.byId(request().username());

        //Finner handlelisteVare-objektene som tilhører paalogget bruker
        List<HandlelisteVare> hl = HandlelisteVare.usersShoppingList(request().username());
        List<Vare> shoppingList=new ArrayList<>();

        //Legger handlelisteVarene, om brukeren har, inn i en egen shoppingList som sendes inn til index (se nederst)
        for(HandlelisteVare v: hl){
            shoppingList.add(Vare.find.byId(v.vareId));
        }

        for(Vare vare:alleVarer){

            if(vare.kategori.equals("elektro")){
                elektro.add(vare);
            }
            if(vare.kategori.equals("fritid")){
                fritid.add(vare);
            }
            if(vare.kategori.equals("jernvare")){
                jernvare.add(vare);
            }
            if(vare.kategori.equals("multimedia")){
                multimedia.add(vare);
            }
            if(vare.kategori.equals("hjem")){
                hjem.add(vare);
            }


        }

        return ok(index.render(request().username(), user, shoppingList, alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class), Form.form(User.class)));
    }

    //Paaloggings kontroller-metoden. Denne tar imot et loginForm fra home.html og undersoeker om paalogging er godkjent
    public static Result authenticate() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();

        for(Vare vare:alleVarer) {

            if (vare.kategori.equals("elektro")) {
                elektro.add(vare);
            }
            if (vare.kategori.equals("fritid")) {
                fritid.add(vare);
            }
            if (vare.kategori.equals("jernvare")) {
                jernvare.add(vare);
            }
            if (vare.kategori.equals("multimedia")) {
                multimedia.add(vare);
            }
            if (vare.kategori.equals("hjem")) {
                hjem.add(vare);
            }
        }


        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            //Paaloggingen gikk ikke, sendes tilbake til startsiden, home.
            return badRequest(home.render(request().username(), alleVarer, elektro, fritid, hjem, jernvare, multimedia, loginForm, Form.form(User.class)));
        } else {
            //Paaloggingen er vellykket, sendes videre til index.
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    //Logg-ut metoden i kontrolleren, sender vinduet tilbake til home.
    @Security.Authenticated(Secured.class)
    public static Result logout() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        User user = User.find.byId(request().username());


        for(Vare vare:alleVarer) {

            if (vare.kategori.equals("elektro")) {
                elektro.add(vare);
            }
            if (vare.kategori.equals("fritid")) {
                fritid.add(vare);
            }
            if (vare.kategori.equals("jernvare")) {
                jernvare.add(vare);
            }
            if (vare.kategori.equals("multimedia")) {
                multimedia.add(vare);
            }
            if (vare.kategori.equals("hjem")) {
                hjem.add(vare);
            }

        }

        session().clear();
        flash("success");
        return ok(home.render(request().username(),alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class), Form.form(User.class)));

    }

    public static Result jsRoutes() {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
                routes.javascript.Application.getTargetVertices()));
    }

    @Security.Authenticated(Secured.class)
    public static Result getTargetVertices() {

        User user = User.find.byId(request().username());
        List<Vare> varer = ShoppingList.getVarerInShoppingList(user.email);


        ArrayList<Vertex> allVerticesInPath = new ArrayList<Vertex>();
        try {
            for (int i = 0; i < Path.finalPath.size() - 1; i++) { //Gaar igjennom alle targetnodene i den rekkefoolgen de skal besookes
                allVerticesInPath.add(Path.finalPath.get(i));
                for (weightedEdge e : Path.wGraph.edges) {
                    if(e.getDestination().equals(Path.finalPath.get(i+1))&&(e.getSource().equals(Path.finalPath.get(i)))){
                        allVerticesInPath.addAll(e.visitedVertices);
                    }
                    if(e.getDestination().equals(Path.finalPath.get(i)) && (e.getSource().equals(Path.finalPath.get(i+1)))){
                        ArrayList<Vertex>reversed=e.visitedVertices;
                        Collections.reverse(reversed);
                        allVerticesInPath.addAll(reversed);
                    }
                }
            }
            allVerticesInPath.add(Vertex.find.byId(18));

            //Setter markoorer
            for (Vare vare : varer) {
                for (int j = 0; j < allVerticesInPath.size(); j++) {
                    if (vare.vertexId == allVerticesInPath.get(j).id) {
                        allVerticesInPath.get(j).setBeskrivelse(vare.navn);
                        allVerticesInPath.get(j).setX(vare.x);
                        allVerticesInPath.get(j).setY(vare.y);
                    }
                }
            }

            return ok(Json.toJson(allVerticesInPath));
        }catch(Exception e){
            return ok(Json.toJson(allVerticesInPath));
        }

    }

    public static Result createUser() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        List<Vare> shoppingList = new ArrayList<>();


        for (Vare vare : alleVarer) {

            if (vare.kategori.equals("elektro")) {
                elektro.add(vare);
            }
            if (vare.kategori.equals("fritid")) {
                fritid.add(vare);
            }
            if (vare.kategori.equals("jernvare")) {
                jernvare.add(vare);
            }
            if (vare.kategori.equals("multimedia")) {
                multimedia.add(vare);
            }
            if (vare.kategori.equals("hjem")) {
                hjem.add(vare);
            }
        }

        Form<User> createUserForm = Form.form(User.class).bindFromRequest();
        if (createUserForm.hasErrors()) {
                return badRequest(home.render(request().username(), alleVarer, elektro, fritid, hjem, jernvare, multimedia, Form.form(Login.class), createUserForm));
        } else {
            User.createUser(createUserForm.get().email, createUserForm.get().password);
            return redirect(
                        routes.Application.home()
                );
            }

    }

    //Finn din handleliste knappen trykkes, det routes til denne metoden, som sender informasjon til shoppingpath-siden
    @Security.Authenticated(Secured.class)
    public static Result shoppingPath() {
        //Kaller paa SortetShoppingList.sortShoppingList(brukers email), som er det metode-kallet som finner handleruten
        //Sorterer handlelisten slik at varene staar i riktig rekkefoelge
        return ok(shoppingPath.render(request().username(), SortedShoppingList.sortShoppingList(request().username())));
    }


}
