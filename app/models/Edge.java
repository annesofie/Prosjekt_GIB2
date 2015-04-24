package models;

/**
 * Created by mathilde on 10/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;

@Entity
public class Edge extends Model{

    @Id
    public int id;
    public int sourceId;
    public int destinationId;


    public static Model.Finder<String, Edge> find = new Model.Finder(String.class, Edge.class);

    public Edge(Integer id, int sourceId, int destinationId) {
        this.id = id;
        this.destinationId=destinationId;
        this.sourceId=sourceId;
    }


    public Vertex getDestination() {
        return Vertex.find.byId(destinationId);
    }

    public Vertex getSource(){
     return Vertex.find.byId(sourceId);
    }

}

