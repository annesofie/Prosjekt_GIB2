package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;




public class Path{

    public Graph graph=new Graph();
    public DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    public ArrayList<Vertex>targets;
    public BreadthFirstSearch bfs=new BreadthFirstSearch();



    public static void main (String[]args){
        System.out.println("Hei");
    }


    public ArrayList<Vertex> findTargets(){
        //Target er vertex'en nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();
        List<Vare> handle=User.handleliste;
        for (Vare vare:handle){
            targets.add(vare.vertex);
        }
        return targets;
    }


    public ArrayList<String> FindGreedyPath(){
        //finner en graadig sti, som kun velger noden med korteste avstand som enda ikke er besøkt
        Vertex rootVertex=graph.rootNode;
        ArrayList<String> sti=new ArrayList<>();
        ArrayList<Vertex> delAvSti=new ArrayList<>();

        for(Vertex target:findTargets()){
            sti.add(bfs.bfs(rootVertex,delAvSti));
            delAvSti.add(rootVertex);
            delAvSti.add(target);
            rootVertex=target;
        }

        return sti;
    }

}