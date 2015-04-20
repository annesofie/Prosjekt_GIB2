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
    public String beskrivelse;
    public int x,y;
    public Vertex prev;

//lagt til key-verdi for å kunne kjoore prims


    public static Finder<Integer, Vertex> find = new Model.Finder(Integer.class, Vertex.class);


    public Vertex(Integer id, Integer x, Integer y) {

        this.id = id;
        this.xPos = x;
        this.yPos = y;

        //felter for bruk av prims-algoritme
        this.parent=null;
        prev=null;

    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public static Vertex getById(int id) {
        return find.byId(id);
    }

    public void setPrevVertex(Vertex v){
        prev=v;
    }

    public void setKey(Integer key){
        this.key=key;
    }

}

