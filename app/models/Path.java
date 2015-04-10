package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;
import java.util.InputMismatchException;
import java.util.Scanner;



public class Path{

    public BreadthFirstSearch bfs=new BreadthFirstSearch();
    public static ArrayList<Vertex>finalPath;


    public Path(Vertex root, Vertex end){
        //finner en graf som innkluderer kun targetnodene
        weightedGraph wGraph=bfs.bfsAllToAll();
        System.out.println("skal ha vært i bfs");
        //kjører saa prims-algoritme paa den vektede grafen for aa finne et minimalt spenntre
        Prims prim=new Prims(root,end, wGraph);
        finalPath=prim.path;
    }

}