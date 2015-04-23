package controllers;

/**
 * Created by annesofiestranderichsen on 11.03.15.
 */
import com.avaje.ebean.Ebean;
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
    public static Result addItem(Long handlelisteVareId){ // husk å endre input!!!!

        User user = User.find.byId(request().username());

        HandlelisteVare hv= new HandlelisteVare(user.email,handlelisteVareId);

        if(HandlelisteVare.usersShoppingList(user.email).contains(hv)){
            //ingenting skjer, varen er allerede i listen
            System.out.println("Varen kan ikke legges til");
        }
        else{

            hv.addVareToHandleliste(user.email, handlelisteVareId);
            //System.out.println("Varen kan legges til");
        }

       /* if(user.handleliste.contains(Vare.find.byId(itemid))){
            //ingenting skjer, varen er allerede i listen
           System.out.println("Varen kan ikke legges til");
        }else{
            user.addToHandleliste(itemid);
            //System.out.println("Varen kan legges til");

        }*/

        return redirect(routes.Application.index());

    }
/*

    @Security.Authenticated(Secured.class)
    public static Result removeItem(Long itemid) {

        User user = User.find.byId(request().username());
        user.removeFromHandleliste(itemid);

        return redirect(routes.Application.index());
    }
*/

    @Security.Authenticated(Secured.class)
    public static Result removeItem(Long vareId){ // husk å endre input!!!!

        User user = User.find.byId(request().username());
        List<HandlelisteVare> h = HandlelisteVare.usersShoppingList(user.email);
        HandlelisteVare vare=null;

        for(HandlelisteVare hv:h){
            if(hv.vareId.equals(vareId)){
                vare=hv;
            }
        }
        HandlelisteVare.removeFromHandleliste(vare);

        return redirect(routes.Application.index());

    }
    public static List<Vare> getVarerInShoppingList(String email){
        List<HandlelisteVare> hv=HandlelisteVare.usersShoppingList(email);
        List<Vare> shoppingList=new ArrayList<>();
        for(HandlelisteVare v: hv){
            shoppingList.add(Vare.find.byId(v.vareId));
        }

        return shoppingList;
    }





}
