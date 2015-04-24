package models;

import java.util.*;

/**
 * Created by mathilde on 14/03/15.
 */
public class weightedGraph {

    public List<Vertex> vertices;
    public static List<weightedEdge> edges;
    public Integer numberOfVertices;
    public Integer numberOfEdges;


    public weightedGraph(ArrayList<Vertex> vertices, ArrayList<weightedEdge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        numberOfEdges=numberOfEdges();
        numberOfVertices=numberOfVertices();
    }

    public Integer numberOfVertices(){
        return vertices.size();
    }

    public Integer numberOfEdges(){
        return edges.size();
    }


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
        return neighbor;
    }

}