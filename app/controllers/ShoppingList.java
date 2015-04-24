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

    //Må ha @security over alle funksjoner som må kaller på request().username()
    @Security.Authenticated(Secured.class)
    public static Result addItem(Long handlelisteVareId){

        User user = User.find.byId(request().username());

        HandlelisteVare hv= new HandlelisteVare(user.email,handlelisteVareId);

        if(HandlelisteVare.usersShoppingList(user.email).contains(hv)){
            //ingenting skjer, varen er allerede lagret på brukeren
        }
        else{
            hv.addVareToHandleliste(user.email, handlelisteVareId);
            //Varen lagres som et handlelisteVare-objekt med brukerens email
        }

        return redirect(routes.Application.index());

    }

    @Security.Authenticated(Secured.class)
    public static Result removeItem(Long vareId){
        User user = User.find.byId(request().username());
        //Oppretter en liste med brukerens handlelistevarer
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
