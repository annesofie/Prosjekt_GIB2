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

    @ManyToMany
    public static List<Vare> handleliste = new ArrayList<>();


    public User(String email, String password, List<Vare> handleliste){
        this.email = email;
        this.password = password;
        this.handleliste = handleliste;
    }

    public static Finder<String,User> find = new Model.Finder(String.class, User.class);


    public static User authenticate(String email, String password){
        return find.where()
                .eq("email", email)
                .eq("password", password).findUnique();

    }

    public static User createUser(String email, String passord, List<Vare> handleliste){
        User user = new User(email, passord, handleliste);
        user.save();
        return user;
    }


    public void addToHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);

        this.handleliste.add(vare);
        this.save();

    }

    public static List<Vare> getShoppingList(){
        return handleliste;
    }

    public static void deleteFromHandleliste(Vare vare, String email){

        handleliste.remove(vare);
    }




}
