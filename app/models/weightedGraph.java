package models;

import java.util.*;

/**
 * Created by mathilde on 14/03/15.
 */
public class weightedGraph {

    public List<Vertex> vertices;
    public final List<weightedEdge> edges;
    public Vertex rootNode;
    public Integer numberOfVertices;
    public Integer numberOfEdges;


    public weightedGraph(ArrayList<Vertex> vertices, ArrayList<weightedEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.rootNode= rootNode;//hent ut startnoden i butikken
        numberOfEdges=numberOfEdges();
        numberOfVertices=numberOfVertices();
    }

    public Integer numberOfVertices(){
        return vertices.size();
    }

    public Integer numberOfEdges(){
        return edges.size();
    }

    //public Iterable<weightedEdge> edges(){}

    //Finner nabonodene til en gitt node
    public List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbor = new ArrayList<>();
        for (weightedEdge edge : this.edges) {
            if (edge.getSource().equals(node)) {
                neighbor.add(edge.getDestination());
            }
            if(edge.getDestination().equals(node)){
                neighbor.add(edge.getSource());
            }
        }
        System.out.println();
        return neighbor;
    }

}