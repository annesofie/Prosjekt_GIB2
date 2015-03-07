package models;

/**
 * Created by annesofiestranderichsen on 03.03.15.
 */
import play.db.ebean.*;
import javax.persistence.*;
import java.util.List;

@Entity
public class User extends Model  {

    @Id
    public String email;
    public String password;
    public static List<Vare> handleliste;


    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public static Finder<String,User> find = new Model.Finder(String.class, User.class);


    public static User authenticate(String email, String password){
        return find.where()
                .eq("email", email)
                .eq("password", password).findUnique();

    }

    public static User createUser(String email, String passord){
        User user = new User(email, passord);
        handleliste=null;
        user.save();
        return user;
    }

    public static void addToHandleliste(Vare vare, String email){
        //må jeg spesifiserer hvem sin handleliste den skal legges i?
        handleliste.add(vare);

    }


    public static void deleteFromHandleliste(Vare vare, String email){
        handleliste.remove(vare);
    }


}
