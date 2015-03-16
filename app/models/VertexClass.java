package models;

/**
 * Created by mathilde on 10/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class VertexClass extends Model{

    @Id
    public int id;
    public int xPos,yPos;





    public static Model.Finder<String, Vertex> find = new Model.Finder(String.class, Vertex.class);


    public VertexClass(Integer id, Integer x, Integer y) {

        this.id = id;
        this.xPos = x;
        this.yPos = y;

    }


}
