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

        //Lager liste over alle noder man skal besøke, for trenger ikke start og sluttnoden med når man skla finne permutasjoner
        List<Vertex> targets=wGraph.vertices;
        targets.remove(Vertex.find.byId(1));
        targets.remove(Vertex.find.byId(18));

        AllPermutations allPerm=new AllPermutations(targets, Vertex.find.byId(1), Vertex.find.byId(18), wGraph);
        finalPath=allPerm.getBestPath();





       // System.out.println("skal ha vært i bfs");
        //kjører saa prims-algoritme paa den vektede grafen for aa finne et minimalt spenntre
       //Prims prim=new Prims(root,end, wGraph);
       //finalPath=prim.path;
    }

}