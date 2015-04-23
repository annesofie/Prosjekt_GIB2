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

    public static Model.Finder<Long, HandlelisteVare> find = new Model.Finder(Long.class, HandlelisteVare.class);

    public HandlelisteVare(String userEmail, Long vareId){
        this.userEmail=userEmail;
        this.vareId= vareId;
    }

    public void addVareToHandleliste(String email, Long vareId){

        Vare vare = Vare.find.byId(vareId);
        vare.in_shoppinglist=true;
        vare.save();
        HandlelisteVare handleliste=new HandlelisteVare(email,vareId);
        Ebean.save(handleliste);
    }

    public void removeFromHandleliste(String email, Long vareId){

        Vare vare = Vare.find.byId(vareId);
        vare.in_shoppinglist=false;
        vare.save();

        HandlelisteVare handleliste=new HandlelisteVare(email,vareId);
        HandlelisteVare h=HandlelisteVare.find.byId(handleliste.handlelisteId);
        Ebean.delete(h);

    }

    public static List<HandlelisteVare> usersShoppingList(String email){
       return find.where()
                .eq("userEmail", email)
                .findList();

    }




}
