package models;

/**
 * Created by annesofiestranderichsen on 03.03.15.
 */
import play.db.ebean.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User extends Model  {

    @Id
    public String email;
    public String password;

    //@OneToMany(cascade=CascadeType.ALL, mappedBy="Vare")
    //public List<Long> handleliste;

    public User(String email, String password){
        this.email = email;
        this.password = password;
        //this.handleliste = new ArrayList<>();
    }

    public static Finder<String,User> find = new Model.Finder(String.class, User.class);


    public static User authenticate(String email, String password){
        return find.where()
                .eq("email", email)
                .eq("password", password).findUnique();

    }


    public static User createUser(String email, String passord){

        User user = new User(email, passord);
        user.save();
        return user;
    }

/*
    public void addToHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);
        vare.in_shoppinglist=true;
        vare.save();
        this.handleliste.add(vare.vareid);
        this.save();

    }*/

   /* public List<Long> getShoppingList(){
        return handleliste;
    }
*/
    /*public void removeFromHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);
        vare.in_shoppinglist=false;
        vare.save();
        this.handleliste.remove(vare.vareid);
        this.save();
    }*/


}
