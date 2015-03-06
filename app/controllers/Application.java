package controllers;

import models.Vare;

import play.*;
import play.mvc.*;

import views.html.*;

import java.util.List;


public class Application extends Controller {

    public static Result index() {

        List<Vare> alleVarer = Vare.find.all();

        return ok(index.render("Finn den varen"));
    }

}
