package controllers;

import models.*;

import play.*;
import play.data.*;
import play.data.Form;
import play.libs.Json;
import play.mvc.*;

import views.html.*;
import java.util.ArrayList;
import java.util.List;


public class Application extends Controller {

    public static BreadthFirstSearch bfs=new BreadthFirstSearch();
    public static ArrayList<Vertex>finalPath;



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
                routes.javascript.Application.getTargetVertices()));
    }

    @Security.Authenticated(Secured.class)
    public static Result getTargetVertices() {

        User user = User.find.byId(request().username());

        List<Vare> varer = user.getShoppingList();
        List<Vertex> vecs = new ArrayList<>();

        for (Vare v : varer) {
            vecs.add(Vertex.getById(v.vertexId));
        }

        return ok(Json.toJson(vecs));

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
        SortedShoppingList sortSH = new SortedShoppingList();

        return ok(shoppingPath.render(request().username(), sortSH.sortShoppingList()));
    }


}
