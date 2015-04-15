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

    @Security.Authenticated(Secured.class)
    public static Result index(){

        User user = User.find.byId(request().username());
        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        List<Vare> shoppingList = user.getShoppingList();




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
        return ok(index.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class), Form.form(User.class)));
    }


    public static Result home() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        List<Vare> shoppingList = new ArrayList<>();



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
        return ok(home.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class), play.data.Form.form(User.class)));
    }


    public static Result authenticate() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        List<Vare> shoppingList = new ArrayList<>();

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
            return badRequest(home.render(request().username(), shoppingList, alleVarer, elektro, fritid, hjem, jernvare, multimedia, loginForm, Form.form(User.class)));
        } else {
            session().clear();
            session("email", loginForm.get().email);
            return redirect(
                    routes.Application.index()
            );
        }
    }

    @Security.Authenticated(Secured.class)
    public static Result logout() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();
        List<Vare> shoppingList = new ArrayList<>();

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
        return ok(home.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class), Form.form(User.class)));

    }

    public static Result jsRoutes() {
        response().setContentType("text/javascript");
        return ok(Routes.javascriptRouter("appRoutes", //appRoutes will be the JS object available in our view
                routes.javascript.Application.getTargetVertices(),
                routes.javascript.Application.getFinalPath()));
    }

    @Security.Authenticated(Secured.class)
    public static Result getTargetVertices() {

        User user = User.find.byId(request().username());
        List<Vare> varer = user.getShoppingList();


        ArrayList<Vertex> allVerticesInPath = new ArrayList<Vertex>();
        try {
            for (int i = 0; i < Path.finalPath.size() - 1; i++) { //Gaar igjennom alle targetnodene i den rekkefoolgen de skal besookes
                allVerticesInPath.add(Path.finalPath.get(i));
                for (weightedEdge e : Path.wGraph.edges) {
                    if(e.getDestination().equals(Path.finalPath.get(i+1))&&(e.getSource().equals(Path.finalPath.get(i)))){
                        System.out.println("trenger ikke reversere, source er "+e.getSource().id+" og destination er "+e.getDestination().id);

                        allVerticesInPath.addAll(e.visitedVertices);
                    }
                    if(e.getDestination().equals(Path.finalPath.get(i)) && (e.getSource().equals(Path.finalPath.get(i+1)))){
                        System.out.println("Må reversere - source er "+e.getSource().id+" og destination er "+e.getDestination().id);

                        ArrayList<Vertex>reversed=e.visitedVertices;
                        Collections.reverse(reversed);
                        System.out.println("Reversed liste er: ");
                        for(Vertex v:reversed){
                            System.out.println(v.id);
                        }
                        allVerticesInPath.addAll(reversed);
                    }
                }
            }

            allVerticesInPath.add(Vertex.find.byId(18));
            System.out.println("alle noder i stien er: ");
            for(Vertex v:allVerticesInPath){
                System.out.println(v.id);
            }

            //Setter markoorer
            for (Vare va : varer) {
                for (int j = 0; j < allVerticesInPath.size(); j++) {
                    if (va.vertexId == allVerticesInPath.get(j).id) {
                        allVerticesInPath.get(j).setBeskrivelse(va.navn);
                    }
                }
            }

            return ok(Json.toJson(allVerticesInPath));
        }catch(Exception e){
            return ok(Json.toJson(allVerticesInPath));
        }

    }


    @Security.Authenticated(Secured.class)
    public static Result getFinalPath() {
        ArrayList<Vertex>allVerticesInPath=new ArrayList<Vertex>();
        return ok(Json.toJson(allVerticesInPath));
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
                return badRequest(home.render(request().username(), shoppingList, alleVarer, elektro, fritid, hjem, jernvare, multimedia, Form.form(Login.class), createUserForm));
        } else {
            User.createUser(createUserForm.get().email, createUserForm.get().password);
            return redirect(
                        routes.Application.home()
                );
            }

    }
    @Security.Authenticated(Secured.class)
    public static Result shoppingPath() {

        User user = User.find.byId(request().username());

        return ok(shoppingPath.render(request().username(), SortedShoppingList.sortShoppingList()));
    }


}
