package models;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.annotation.ConcurrencyMode;
import com.avaje.ebean.annotation.EntityConcurrencyMode;
import play.db.ebean.Model;

import javax.persistence.*;


import java.util.*;

/**
 * Created by annesofiestranderichsen on 23.04.15.
 */

@Entity

public class HandlelisteVare extends Model{

    @Id
    public Long handlelisteId;
    public String userEmail;
    public Long vareId;
    //public boolean chosen;

    public static Model.Finder<Long, HandlelisteVare> find = new Model.Finder(Long.class, HandlelisteVare.class);

    public HandlelisteVare(String userEmail, Long vareId){
        this.userEmail=userEmail;
        this.vareId= vareId;
        //this.chosen=false;
    }

    public void addVareToHandleliste(String email, Long vareId){

        Vare vare = Vare.find.byId(vareId);
        vare.in_shoppinglist=true;
        vare.save();
        HandlelisteVare handleliste=new HandlelisteVare(email,vareId);
        Ebean.save(handleliste);
    }

    public static void removeFromHandleliste(HandlelisteVare vare){

        HandlelisteVare hv = HandlelisteVare.find.byId(vare.handlelisteId);
        //chosen=ShoppingList.setChosen();
        //vare.save();

        Vare vareTo = Vare.find.byId(hv.vareId);
        vareTo.in_shoppinglist=false;
        vareTo.save();

        Ebean.delete(vare);

    }

    public static List<HandlelisteVare> usersShoppingList(String email){
       return find.where()
                .eq("userEmail", email)
                .findList();

    }






}
