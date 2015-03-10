package models;

/**
 * Created by mathilde on 09/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class NodeskalIkkeBrukes extends Model {

    @Id
    public int nodenummer;
    public int positionX, positionY, kantVerdi;


    public static Model.Finder<Integer, NodeskalIkkeBrukes> find = new Model.Finder(Integer.class, NodeskalIkkeBrukes.class);

    public NodeskalIkkeBrukes(int node, int x, int y, int kant){
        nodenummer=node;
        positionX=x;
        positionY=y;
        kantVerdi=kant;

    }

}

