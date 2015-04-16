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


    public Path(Vertex root, Vertex end){
        //finner en graf som innkluderer kun targetnodene
        wGraph=bfs.bfsAllToAll();

        //Lager liste over alle noder man skal besøkes, for trenger ikke start og sluttnoden med når man skal finne permutasjoner
        List<Vertex> targets=wGraph.vertices;
        targets.remove(Vertex.find.byId(1));
        targets.remove(Vertex.find.byId(18));

        //Finner alle mulige måter å travesere grafen på
        AllPermutations allPerm=new AllPermutations(targets, Vertex.find.byId(1), Vertex.find.byId(18), wGraph);
        //Finner beste sti ved å si hvilken som totalt sett er kortest
        finalPath=allPerm.getBestPath();

    }

}