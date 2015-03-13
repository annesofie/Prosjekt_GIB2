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
    private final String id;
    private final Vertex source;
    private final Vertex destination;
    private final int weight;

    public static Model.Finder<String, Edge> find = new Model.Finder(String.class, Edge.class);

    public Edge(String id, Vertex source, Vertex destination, int weight) {
        this.id = id;
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    public String getId() {
        return id;
    }

    public Vertex getDestination() {
        return destination;
    }

    public Vertex getSource() {
        return source;
    }
    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return source + " " + destination;
    }
}