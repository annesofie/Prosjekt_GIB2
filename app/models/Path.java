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

        int big=0;

        if(targets.size()>8){
            System.out.println("riktig if");
            big=2;
            shortestFromEachSide path=new shortestFromEachSide(targets,wGraph);
            finalPath=shortestFromEachSide.path;



        }
        //Trenger trolig ikke denne likevel
        else if(targets.size()>18) {

            big = 1;
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

            AllPermutations allPerm = new AllPermutations(targets, Vertex.find.byId(1), Vertex.find.byId(18), wGraph);
            finalPath = allPerm.getBestPath(big);
        }


        

        else{
            big=0;
            AllPermutations allPerm = new AllPermutations(targets, Vertex.find.byId(1), Vertex.find.byId(18), wGraph);
            finalPath = allPerm.getBestPath(big);
        }


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