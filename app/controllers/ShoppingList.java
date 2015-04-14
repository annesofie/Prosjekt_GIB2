package controllers;

/**
 * Created by annesofiestranderichsen on 11.03.15.
 */
import models.*;

import play.*;
import play.data.*;
import play.data.Form;
import play.mvc.*;

import views.html.*;
import java.util.ArrayList;
import java.util.List;

public class ShoppingList extends Controller {

    //Må ha @security over alle funksjoner som kaller på request().username()
    @Security.Authenticated(Secured.class)
    public static Result addItem(Long itemid){

        User user = User.find.byId(request().username());

        if(user.handleliste.contains(Vare.find.byId(itemid))){
            //ingenting skjer, varen er allerede i listen
            System.out.println("Varen kan ikke legges til");
        }else{
            user.addToHandleliste(itemid);
            //System.out.println("Varen kan legges til");

        }

        //routes.Application.getTargetVerticec(); Route til flere ting samtidig???

        return redirect(routes.Application.index());

    }

    @Security.Authenticated(Secured.class)
    public static Result removeItem(Long itemid) {

        User user = User.find.byId(request().username());
        user.removeFromHandleliste(itemid);

        return redirect(routes.Application.index());
    }




}
