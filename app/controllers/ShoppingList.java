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

    @Security.Authenticated(Secured.class)
    public static Result addItem(Long itemid){

        System.out.println(request().username());
        User user = User.find.byId(request().username());
        user.addToHandleliste(itemid);

        return redirect(routes.Application.index());

    }
}
