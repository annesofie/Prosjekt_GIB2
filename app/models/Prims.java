package models;

import java.util.*;

/**
 * Created by mathilde on 16/03/15.
 */
public class Prims {


    public static final int INFINITE = 999;
    public Vertex root=Vertex.find.byId(1);
    public Vertex end=Vertex.find.byId(18);
    weightedGraph wGraph;
    ArrayList<Vertex>path;
    //kjører prims-algoritme for å finne minimalt spenntre

    public Prims(Vertex root, Vertex end, weightedGraph wGraph) {
        this.root=root;
        this.wGraph=wGraph;
        path=primsExecuteTwerked();
        this.end=end;

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
            q.addAll(wGraph.vertices);
            q.remove(end);
            q.remove(getClosestToEndVertex());
            
            for(Vertex v:q){
                //System.out.println("I queue ligger: "+v.id);
            }


            Vertex u;
            //System.out.println("Legger til root i path");
            path.add(root);
            visited.add(root);
            //har allerede satt alle noder's key til infinite, og forelder til null i Vertex klassen
            root.key = 0;
            int min = INFINITE;
            while (!q.isEmpty()) {
                u = q.poll();
                //System.out.println("u er: " + u.id);
                for (Vertex neighbor : wGraph.getNeighbors(u)) {
                    if (q.contains(neighbor)&& (!visited.contains(neighbor))) {
                        //System.out.println("q inneholder "+neighbor.id);
                        for (weightedEdge edge : wGraph.edges) {
                            if (((edge.getDestination().equals(neighbor) && (edge.getSource().equals(u))) || ((edge.getDestination().equals(u)) && (edge.getSource().equals(neighbor))))) {
                                //System.out.println("i forløkke, og nabo er "+neighbor.id+" og u er: "+u.id);
                                neighbor.key = edge.weight;
                                neighbor.parent = u;
                                path.add(neighbor);
                                visited.add(neighbor);
                            }
                        }
                    }
                }
            }


            for (Vertex v : wGraph.vertices) {  //Resetter alle nodenes verdier
                v.key = INFINITE;
                v.parent = null;
            }
            path.add(getClosestToEndVertex());
            path.add(end);
        }
        //System.out.println("Path er: ");
        for(Vertex v:path){
            //System.out.println(v.id);
        }

        return path;
    }


    public Vertex getClosestToEndVertex() {
        List<Vertex> neighbor = new ArrayList<>();
        int w=INFINITE;
        Vertex closest=null;
        Vertex node=Vertex.find.byId(18); //Setter kassenoden som endenoden i butikken

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(node)&& edge.weight<w) {
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(node) && edge.weight<w){
                closest= edge.getSource();
                w=edge.weight;
            }
        }

        return closest;
    }

}