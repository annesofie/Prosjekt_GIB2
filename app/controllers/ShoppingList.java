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
            System.out.println("Varen kan legges til");

        }

        return redirect(routes.Application.index());

    }

    @Security.Authenticated(Secured.class)
         public static Result removeItem(Long itemid) {

        User user = User.find.byId(request().username());
        user.removeFromHandleliste(itemid);

        return redirect(routes.Application.index());
    }

    public static List<Vare> sortShoppingList() {
        Path path = new Path(1,100); //legg inn rotnodenummer og kassenodenummer i input
        List<Vertex>p=path.finalPath;
        List<Vare> sortedShoppingList = new ArrayList<Vare>();

        for (Vertex vertex: p) {
            for (Vare vare: User.handleliste) {
                if (vare.vertex == vertex) {
                    sortedShoppingList.add(vare);
                }
            }
        }

        return sortedShoppingList;
    }

}
