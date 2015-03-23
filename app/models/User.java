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
    public List<Vare> sortedHandleliste;
    public Path path=new Path(Vertex.find.byId("0"), Vertex.find.byId("100"));

    @ManyToMany
    public static List<Vare> handleliste = new ArrayList<>();


    public User(String email, String password, List<Vare> handleliste){
        this.email = email;
        this.password = password;
        this.handleliste = handleliste;
        this.sortedHandleliste=sortShoppingList(Path.finalPath);

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

    public void removeFromHandleliste(Long vareid){

        Vare vare = Vare.find.byId(vareid);

        this.handleliste.remove(vare);
        this.save();
    }

    public static List<Vare> sortShoppingList(List<Vertex> vertices) {

        List<Vare> sortedShoppingList = new ArrayList<Vare>();

        for (Vertex vertex: vertices) {
            for (Vare vare: handleliste) {
                if (vare.vertexId == vertex.id) {
                    sortedShoppingList.add(vare);
                }
            }
        }

        return sortedShoppingList;
    }


}
