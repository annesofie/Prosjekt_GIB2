package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;




public class Path{

    public Graph graph=new Graph();
    public DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    public ArrayList<Vertex>targets;

    public ArrayList<Vertex> nodeListVisited;
    public Vertex current;


    public static void main (String[]args){
        System.out.println("Hei");
    }

    public ArrayList<Vertex> findTargets(String email){
        //Target er vertex'en nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();
        List<Vare> handle=User.handleliste;
        for (Vare vare:handle){
            targets.add(vare.vertex);
        }
        return targets;
    }


    //Returenerer nodene i stiene som viser korteste vei fra start node til alle andre noder
    public ArrayList<ArrayList<Vertex>> noderIStiene(String email){
        return dijkstra.getPathToAllRelevantNodes(findTargets(email));

    }




    public ArrayList<Edge> FindGreedyPath(String email, Vertex start, Vertex slutt){

        //finner en graadig sti, som kun velger noden med korteste avstand som enda ikke er besøkt
        ArrayList<ArrayList<Vertex>>relevanteStier=noderIStiene(email);
        ArrayList<Vertex> greedyPath=new ArrayList<Vertex>();
        int minWeight=10000000;
        int pathWeight=0;
        ArrayList<Vertex> nextVertices=new ArrayList();
        for (ArrayList<Vertex> list:relevanteStier){                        //for hver sti fra relevante stier
            for(int i=0; i<list.size()-1;i++) {                              //for hver node i stien
                for (Edge edge : Graph.getEdges()) {                           //for hver kant i grafen, går den fra noden til neste node i stien?
                    if ((edge.getSource().equals(graph.getVertices().get(i))) && (edge.getDestination().equals(graph.getVertices().get(i + 1)))) {
                        pathWeight += edge.getWeight();                     //isaafall, lagre kantvekten som en del av stien
                    }
                }
            }
            if(pathWeight<minWeight){
                minWeight=pathWeight;

                greedyPath.add(list.get(list.size()-1)); //Lagrer siste noden i stien, som er noden som er neermest, og en del av den graadige stien.
            }
        }



        List<Edge> allEdges= graph.getEdges();
        ArrayList<Edge>pathEdges=new ArrayList<Edge>();


        for (Edge edge : allEdges){
            if(noderIStiene(email).contains(edge)){
                pathEdges.add(edge);
            }
        }

        return pathEdges;

    }


}