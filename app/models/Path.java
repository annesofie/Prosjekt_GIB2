package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;


public class Path{

    public Graph graph=new Graph();
    public DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    public ArrayList<Vertex>targets;


    public static void main(String[]args){

    }

    public ArrayList<Vertex> findTargets(String email){
        //Target er vertex'en nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();
        List<Vare> handle=User.handleliste;
        for (Vare vare:handle){
            targets.add(vare.vertex);
        }
        return targets;
    }


    public ArrayList<LinkedList> noderIStiene(String email){
        return dijkstra.getPathToAllRelevantNodes(findTargets(email));

    }

    public ArrayList<Vertex> nodeListVisited;
    public Vertex current;

    public ArrayList<Edge> FindPath(String email, Vertex start, Vertex slutt){

        //finner en graadig sti, som kun velger noden med korteste avstand som enda ikke er besøkt

        List<Edge> allEdges= graph.getEdges();
        ArrayList<Edge>pathEdges=new ArrayList<Edge>();
        for (Edge edge : allEdges){                                                                    ArrayList<Edge> path=
            if(noderIStiene(email).contains(edge)){
                pathEdges.add(edge);
            }                                                                                          for(Vertex vertex:current
        }

    }


}