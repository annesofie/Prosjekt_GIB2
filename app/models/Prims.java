package models;

import java.util.*;

/**
 * Created by mathilde on 16/03/15.
 */
public class Prims {


    public static final int INFINITE = 999;
    public Vertex root;
    public Vertex end;
    weightedGraph wGraph;
    ArrayList<Vertex>path;
    //kjører prims-algoritme for å finne minimalt spenntre

    public Prims(Vertex root, Vertex end, weightedGraph wGraph) {
        this.root=root;
        this.wGraph=wGraph;
        this.end=end;
        path=primsExecuteTwerked();

    }

    static class vertexComparator implements Comparator<Vertex> {

        public int compare(Vertex v1, Vertex v2){
            System.out.println("Compare");
            if(v1.key==v2.key){
                System.out.println("keys er like, "+v1.id+", "+v1.key+", "+v2.id+", "+v2.key);
                return 0;
            }
            else
                System.out.println(v1.id+" sin key er: "+v1.key+ " og " +v2.id+" sin key er: "+v2.key);
                return (v1.key-v2.key);
        }
    }

    public ArrayList<Vertex> primsExecuteTwerked() {
        ArrayList<Vertex> path = new ArrayList<>();

        if (wGraph.vertices.size() < 4) {

            path.add(root);
            for (Vertex v : wGraph.vertices){
                if (!v.equals(root) && (!v.equals(end))) {
                    path.add(v);
                }
            }
            path.add(end);
            //System.out.println("kun en vare, og path blir: ");
            for(Vertex v:path){
               //System.out.println(v.id);
            }
        }

        else { //Hvis man skal innom mer enn en vare
            vertexComparator comp = new vertexComparator();
            PriorityQueue<Vertex> q = new PriorityQueue<>(wGraph.edges.size(), comp);
            ArrayList<Vertex>visited=new ArrayList<>();
            setInfiniteKeys();
            q.addAll(wGraph.vertices);
            q.remove(end);
            Vertex closest=getClosestToEndVertex();
            q.remove(closest);

            for(Vertex v:q){
                if(v.equals(root)){
                    v.key=0;
                }
                System.out.println("I queue ligger: "+v.id+" og har key: "+v.key);
            }
            Vertex u;
            //path.add(root);
            visited.add(root);
            //har allerede satt alle noder's key til infinite, og forelder til null i Vertex klassen

            int min = INFINITE;
            while (!q.isEmpty()) {
                u = q.poll();
                if(u.parent!=null){
                    path.add(u.parent);
                }
                System.out.println("u er: " + u.id);
                for (Vertex neighbor : wGraph.getNeighbors(u)) {
                    if (q.contains(neighbor)&& (!(visited.contains(neighbor)))){
                        System.out.println("q inneholder "+neighbor.id+" og den er ikke allerede besøkt");
                        for (weightedEdge edge : wGraph.edges) { //Finner kanten mellom noden og naboen dens
                            if (((edge.getDestination().equals(neighbor) && (edge.getSource().equals(u))) || ((edge.getDestination().equals(u)) && (edge.getSource().equals(neighbor))))) {
                                if(edge.weight<neighbor.key){
                                    System.out.println("i forløkke, og nabo er "+neighbor.id+" og u er: "+u.id+" og key blir oppdatert til: "+edge.weight);
                                    for(Vertex n:q) {
                                        if (n.equals(neighbor)) {
                                            n.key = edge.weight;
                                            n.parent = u;
                                        }
                                    }
                                    path.add(neighbor);
                                    visited.add(neighbor);
                                }
                            }
                        }
                    }
                }
            }


            for (Vertex v : wGraph.vertices) {  //Resetter alle nodenes verdier
                v.key = 9999;
                v.parent = null;
            }
            path.add(closest);
            path.add(end);
        }

        return path;
    }


    public Vertex getClosestToEndVertex() {
        int w=INFINITE;
        Vertex closest=null;
        Vertex node=end; //Setter kassenoden som endenoden i butikken

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(node)&& edge.weight<w &&(!closestToStart(edge.getDestination()))){
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(node) && edge.weight<w && (!closestToStart(edge.getSource()))){
                closest= edge.getSource();
                w=edge.weight;
            }
        }

        return closest;
    }

    public boolean closestToStart(Vertex vertex){

        int w=INFINITE;
        Vertex closest=null;
        Vertex root=this.root;

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(root)&& edge.weight<w){
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(root) && edge.weight<w){
                closest= edge.getSource();
                w=edge.weight;
            }
        }

        return (closest.equals(vertex));


    }

    public void setInfiniteKeys(){
        for(Vertex v:wGraph.vertices){
            v.key=9999;
        }
    }


}