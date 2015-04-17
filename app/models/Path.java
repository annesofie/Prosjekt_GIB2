package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Path{

    public BreadthFirstSearch bfs=new BreadthFirstSearch();
    public static List<Vertex>finalPath;
    public static weightedGraph wGraph;

    public static List<Vertex> closeToEnd;
    public static List<Vertex> closeToStart;

    public Path(Vertex root, Vertex end){
        //finner en graf som innkluderer kun targetnodene
        wGraph=bfs.bfsAllToAll();

        for(weightedEdge e:wGraph.edges) {
            System.out.println("kant destination: "+e.getDestination().id+" soruce: "+e.getSource().id+ " og vekt: "+e.weight);
        }

        //Lager liste over alle noder man skal besøkes, for trenger ikke start og sluttnoden med når man skal finne permutasjoner
        List<Vertex> targets=wGraph.vertices;
        targets.remove(Vertex.find.byId(1));
        targets.remove(Vertex.find.byId(18));

        boolean big;
        if(targets.size()>7) {
            big = true;
            ArrayList<Vertex> visited=new ArrayList<>();
            closeToStart=new ArrayList<>();
            closeToEnd=new ArrayList<>();

            //finner de to nærmeste nodene til startnoden
            visited.add(root);
            Vertex closestToRoot=closestTo(root, visited);
            closeToStart.add(closestToRoot);
            visited.add(closestToRoot);

            Vertex closestNext=closestTo(closestToRoot,visited);
            closeToStart.add(closestNext);

            //Finner de to nærmeste nodene til kassenoden
            Vertex closestToEnd=closestTo(end, visited);
            closeToEnd.add(closestToEnd);
            visited.add(closestToEnd);

            closestNext=closestTo(closestToEnd,visited);
            closeToEnd.add(closestNext);

            //Fjerner de fra targets for å minske antall permutasjoner
            targets.removeAll(closeToEnd);
            targets.removeAll(closeToStart);
        }
        

        else{
            big=false;

        }
        //Finner alle mulige måter å travesere grafen på
        AllPermutations allPerm = new AllPermutations(targets, Vertex.find.byId(1), Vertex.find.byId(18), wGraph);

        for (List<Vertex> list : allPerm.allPermutations) {
            System.out.println("perm: ");
            for (Vertex v : list) {
                System.out.println(v.id);
            }
        }

        //Finner beste sti ved å si hvilken som totalt sett er kortest
        finalPath = allPerm.getBestPath(big);

    }

    public static Vertex closestTo(Vertex vertex, ArrayList<Vertex> visited){

        int w=99999;
        Vertex closest=Vertex.find.byId(1);

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(vertex)&& (edge.weight<w) && (!visited.contains(edge.getDestination())) ){
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(vertex) && (edge.weight<w) && (!visited.contains(edge.getSource())) ) {
                closest= edge.getSource();
                w=edge.weight;
            }
        }

        return (closest);

    }

}