package models;

/**
 * Created by mathilde on 10/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;

@Entity
public class Edge extends Model{

    //Vi må huske å lagre to kanter mellom nodene, fordi dijsktra kjører på directed graphs, og vi oonsker at man skal kunne gå begge retninger
    @Id
    public int id;
    public Vertex source;
    public Vertex destination;
<<<<<<< HEAD
    public int sourceId;
    public int destinationId;
=======
>>>>>>> origin/master


    public static Model.Finder<String, Edge> find = new Model.Finder(String.class, Edge.class);

    public Edge(Integer id, int sourceId, int destinationId) {
        this.id = id;
        this.source = Vertex.find.byId(sourceId);
        this.destination = Vertex.find.byId(destinationId);
    }


    public Integer getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }


    @Override
    public String toString() {
        return source + " " + destination;
    }
}