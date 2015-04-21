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


    public User(String email, String password){
        this.email = email;
        this.password = password;
        this.handleliste = new ArrayList<>();
        //this.sortedHandleliste=sortShoppingList(Path.finalPath);
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


    public void addToHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);
        vare.in_shoppinglist=true;
        vare.save();
        this.handleliste.add(vare);
        this.save();

    }

    public static List<Vare> getShoppingList(){
        return handleliste;
    }

    public void removeFromHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);
        vare.in_shoppinglist=false;
        vare.save();
        this.handleliste.remove(vare);
        this.save();
    }

    public static List<Vare> sortShoppingList(List<Vertex> vertices) {

        List<Vare> sortedShoppingList = new ArrayList<Vare>();

        for (Vertex vertex: vertices) {
            for (Vare vare: handleliste) {
                if (vare.vertex == vertex) {
                    sortedShoppingList.add(vare);
                }
            }
        }

        return sortedShoppingList;
    }


}
