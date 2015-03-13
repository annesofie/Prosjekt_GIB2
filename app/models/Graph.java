package models;

import play.db.ebean.Model;

import java.util.*;
/**
 * Created by mathilde on 10/03/15.
 */


public class Graph extends Model{

    public final List<Vertex> vertexes;
    public final List<Edge> edges;
    public Vertex rootNode;


    public Graph() {
        this.vertexes = getVertices();
        this.edges = getEdges();
        this.rootNode= rootNode;//hent ut startnoden i butikken
    }


    public List<Vertex> getVertices() {
        return Vertex.find.all();

    }

    public static List<Edge> getEdges() {
        return Edge.find.all();
    }

}
