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
    public static Vertex root;
    public Vertex end;

    public static List<Vertex> closeToEnd;
    public static List<Vertex> closeToStart;
    public User user;

    public Path(User user){
        this.end=Vertex.find.byId(18);
        this.root=Vertex.find.byId(1);
        this.user=user;

        //finner en graf som innkluderer kun targetnodene
        wGraph=bfs.bfsAllToAll(root,end, user);

        //Lager liste over alle noder man skal besoekes, trenger ikke start og sluttnoden med naar man skal finne permutasjoner
        List<Vertex> targets=wGraph.vertices;
        targets.remove(root);
        targets.remove(end);



        if(targets.size()>7){
            shortestFromEachSide path=new shortestFromEachSide(targets,wGraph);
            finalPath=shortestFromEachSide.path;
        }
        else{

            AllPermutations allPerm = new AllPermutations(targets, root, end, wGraph);
            finalPath = allPerm.getBestPath();
        }
    }


}