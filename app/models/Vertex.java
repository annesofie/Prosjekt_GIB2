package models;

/**
 * Created by mathilde on 10/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Vertex extends Model{

    @Id
    public int id;
    public int xPos,yPos;
    public int key;
    public Vertex parent;
    public int INFINITE = 999;
    public String beskrivelse;
    

//lagt til key-verdi for å kunne kjoore prims


    public static Finder<Integer, Vertex> find = new Finder(Integer.class, Vertex.class);




    public Vertex(Integer id, Integer x, Integer y) {

        this.id = id;
        this.xPos = x;
        this.yPos = y;
        this.key=INFINITE;
        this.parent=null;

    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public static Vertex getById(int id) {
        return find.byId(id);
    }

}