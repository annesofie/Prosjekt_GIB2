package controllers;

import models.*;

import play.*;
import play.data.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;
import java.util.ArrayList;
import java.util.List;


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
        return ok(index.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class)));
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
        return ok(home.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class)));
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
            return badRequest(home.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, loginForm));
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
        return ok(home.render(request().username(),shoppingList ,alleVarer, elektro, fritid, hjem, jernvare, multimedia, play.data.Form.form(Login.class)));

    }

}