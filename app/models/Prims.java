package models;

import java.util.*;

/**
 * Created by mathilde on 16/03/15.
 */
public class Prims {


    public static final int INFINITE = 999;
    public Vertex root;
    weightedGraph wGraph;

    //kjører prims-algoritme for å finne minimalt spenntre

    public Prims(Vertex root, weightedGraph wGraph) {
        this.root=root;
        this.wGraph=wGraph;
        primsExecute();

    }

    static class vertexComparator implements Comparator<Vertex> {

        public int compare(Vertex v1, Vertex v2){
            if(v1.key==v2.key){
                return 0;
            }
            else
                return (v1.key-v2.key);
        }
    }

//Maa fikse at sluttnoden er bestem til kassenoden. Har ikke funnet ut hvordan det skal gjoores enda.
    public ArrayList<Vertex> primsExecute(){
        vertexComparator comp=new vertexComparator();
        PriorityQueue<Vertex> q = new PriorityQueue<>(wGraph.edges.size(),comp);
        q.addAll(wGraph.vertices);
        ArrayList<Vertex> path=new ArrayList<>();
        path.add(root);
        Vertex u;
        //har allerede satt alle noder's key til infinite, og forelder til null i Vertex klassen
        root.key=0;
        int min=INFINITE;
        while(!q.isEmpty()){
            u=q.poll();
            for (Vertex neighbor: wGraph.getNeighbors(u)){
                if(q.contains(neighbor)){
                    for(weightedEdge edge:wGraph.edges){
                        if((edge.getDestination().equals(neighbor)&&(edge.getSource().equals(u)))||(edge.getDestination().equals(u)&&(edge.getSource().equals(neighbor)))){
                            neighbor.key=edge.weight;
                            neighbor.parent=u;
                            path.add(neighbor);
                        }
                    }
                }
            }
        }


        for(Vertex v:wGraph.vertices){  //Resetter alle nodenes verdier
            v.key=INFINITE;
            v.parent=null;
        }

        return path;
    }

}