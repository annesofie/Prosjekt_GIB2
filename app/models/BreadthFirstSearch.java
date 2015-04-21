package models;

import java.util.PriorityQueue;
import java.util.*;

/**
 * Created by mathilde on 13/03/15.
 */
public class BreadthFirstSearch {

    Graph graph = new Graph();
    ArrayList<Vertex> visited;
    public ArrayList<Vertex>targets;
    List<Edge> edges;
    HashMap<Edge, Vertex> destinations;
    HashMap<Edge, Vertex> sources;



    //Skal returnere en graf som viser vei fra alle target-noder til alle target-noder, et spenntre
    public weightedGraph bfsAllToAll(Vertex root, Vertex end) {
        edges= Graph.getEdges();
        // BFS uses Queue data structure
        visited=new ArrayList<>();
        Queue queue = new LinkedList();
        ArrayList<Vertex> graphVertices=new ArrayList<>();
        ArrayList<weightedEdge> graphEdges=new ArrayList<>();
        destinations=makeHashMapForEdgeDestinations();
        sources=makeHashMapForEdgeSources();

        //Legger til alle targetnoder i vektet graf, vet at det finnes sti til alle saa kan bare legge de til med en gang
        for(Vertex t:findTargets()){
            graphVertices.add(t);
        }


        //Legger ogsaa til start og sluttnoden, fordi de ogsaa alltid er en del av den nye grafen
        graphVertices.add(root);
        graphVertices.add(end);


        for (Vertex rootVertex:graphVertices){
            queue.add(rootVertex);
            visited.add(rootVertex);

            while (!(queue.isEmpty())) { //Så lenge det finnes ubesøkte noder
                Vertex vertex = (Vertex)queue.remove();
                Vertex child = null;
                while ((child = getUnvisitedChildNodeInGraph(vertex)) != null) { //Så lenge "vertex" har ubesøkte barn
                    child.setPrevVertex(vertex);

                    if (graphVertices.contains(child)) { //Hvis noden er en target-node
                        ArrayList<Vertex> visitedVertices= new ArrayList<>();
                        boolean rootV=false;
                        Vertex v=child;

                        //Finner alle nodene som ligger i stien til den aktuelle target-noden
                        while(!rootV && v.prev!=null){
                            v=v.prev;
                            visitedVertices.add(v); //Legger til nodene som er besokt imellom
                            if(v.equals(rootVertex)){
                                rootV=true;
                                visitedVertices.remove(v);
                            }

                        }

                        boolean equal=false;

                        //sjekker om det finnes en kant mellom samme sett med noder, men motsatt vei.
                        // Hvis det er tilfelle trenger vi ikke lagre kanten på nytt, fordi den aktuelle informasjonene allerede eksisterer
                        for(weightedEdge we:graphEdges){
                            if(we.getDestination().equals(child)&&(we.getSource().equals(rootVertex))){
                                equal=true;
                            }
                        }

                        //Opretter en vektet kant mellom rotnode og targetnoden

                        weightedEdge e=new weightedEdge(child, rootVertex, visitedVertices.size()+1, visitedVertices);

                        if(!equal){
                            graphEdges.add(e);
                        }

                    }
                    visited.add(child);
                    queue.add(child);
                }

            }
            visited.clear();
            queue.clear();

        }

        //opretter en vektet graf som kunne innoholder relevant informasjon
        return new weightedGraph(graphVertices,graphEdges);
    }


    //TESTET OG OK!!!
    public ArrayList<Vertex> findTargets(){

        //Target er vertex'en på stien som er nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();

        List<Vare> handleVarer=User.handleliste;
        for (Vare vare:handleVarer){
            targets.add(vare.findVareVertex());
        }


        return targets;
    }


    //TESTET ok:)
    public Vertex getUnvisitedChildNodeInGraph(Vertex vertex) {
        for (Vertex child:getChildren(vertex)){
            if(!visited.contains(child)){
                return child;
            }
        }
        return null;
    }

    //Finner nabonodene til en gitt node
    public List<Vertex> getChildren(Vertex vertex){

        List<Vertex> children = new ArrayList<>();
        for (Edge edge : edges) {
            if (sources.get(edge).equals(vertex)){
                children.add(destinations.get(edge));
            }
        }
        for(Vertex child:children) {
        }
        return children;
    }

    // <3<3<3<3<3<3<3<3<3<3 reddet alt

    //opretter en lokal hashmap som inneholder alle sources og destinations til alle kantene, slik at man slipper å kalle på databasen hver gang

    public HashMap<Edge,Vertex> makeHashMapForEdgeSources(){
        HashMap<Edge, Vertex> s=new HashMap<>();
        for (Edge edge : Graph.getEdges()) {
               s.put(edge,edge.getSource());
        }
        return s;
    }

    public HashMap<Edge,Vertex> makeHashMapForEdgeDestinations(){
        HashMap<Edge, Vertex> d=new HashMap<>();
        for (Edge edge : Graph.getEdges()) {
            d.put(edge,edge.getDestination());
        }
        return d;

    }
}

