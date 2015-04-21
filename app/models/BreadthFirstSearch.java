package models;

import java.util.PriorityQueue;
import java.util.*;

/**
 * Created by mathilde on 13/03/15.
 */
public class BreadthFirstSearch {
    long startTime=System.nanoTime();

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

        System.out.println("1");
        //Legger ogsaa til start og sluttnoden, fordi de ogsaa alltid er en del av den nye grafen
        graphVertices.add(root);
        graphVertices.add(end);
        System.out.println("2");

        for (Vertex rootVertex:graphVertices){
            queue.add(rootVertex);
            visited.add(rootVertex);
            System.out.println("3");
            while (!(queue.isEmpty())) { //Så lenge det finnes ubesøkte noder
                Vertex vertex = (Vertex)queue.remove();
                Vertex child = null;
                while ((child = getUnvisitedChildNodeInGraph(vertex)) != null) { //Så lenge "vertex" har ubesøkte barn
                    child.setPrevVertex(vertex);
                    System.out.println("4");
                    if (graphVertices.contains(child)) { //Hvis noden er en target-node
                        ArrayList<Vertex> visitedVertices= new ArrayList<>();
                        boolean rootV=false;
                        Vertex v=child;
                        System.out.println("5");
                        //Finner alle nodene som ligger i stien til den aktuelle target-noden
                        while(!rootV && v.prev!=null){
                            v=v.prev;
                            visitedVertices.add(v); //Legger til nodene som er besokt imellom
                            if(v.equals(rootVertex)){
                                rootV=true;
                                visitedVertices.remove(v);
                            }
                            System.out.println("6");
                        }
                        System.out.println("7");
                        boolean equal=false;

                        //sjekker om det finnes en kant mellom samme sett med noder, men motsatt vei.
                        // Hvis det er tilfelle trenger vi ikke lagre kanten på nytt, fordi den aktuelle informasjonene allerede eksisterer
                        for(weightedEdge we:graphEdges){
                            if(we.getDestination().equals(child)&&(we.getSource().equals(rootVertex))){
                                equal=true;
                            }
                        }
                        System.out.println("8");
                        //Opretter en vektet kant mellom rotnode og targetnoden
                        System.out.println("skal til å begynne å opprette new vektet kant");
                        weightedEdge e=new weightedEdge(child, rootVertex, visitedVertices.size()+1, visitedVertices);
                        System.out.println("opprettet ny vektet kant new vektet kant");
                        if(!equal){
                            graphEdges.add(e);
                        }
                        System.out.println("9");
                    }
                    visited.add(child);
                    queue.add(child);
                }
                System.out.println("10");
            }
            System.out.println("11");
            visited.clear();
            queue.clear();
            System.out.println("12");
        }

        long endTime=System.nanoTime();
        System.out.println("BFS tar i millisek: "+((endTime-startTime)/1000000));

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

        long starT=System.nanoTime();
        System.out.println("startTid i getUnvisited... er: "+starT);
        System.out.println("i getUnvisitedChildNodeInGraph");
        for (Vertex child:getChildren(vertex)){
            System.out.println("for hvert barn");
            if(!visited.contains(child)){
                System.out.println("ubesøkt");
                return child;
            }
        }
        System.out.println("ferdig i getUnvisitedChildNodeInGraph");
        long endT=System.nanoTime();
        System.out.println("getUnvisitedChild... bruker : "+((endT-starT)/1000000));
        return null;
    }

    //Finner nabonodene til en gitt node
    public List<Vertex> getChildren(Vertex vertex){
        System.out.println("get children");

        List<Vertex> children = new ArrayList<>();
        for (Edge edge : edges) {
            if (sources.get(edge).equals(vertex)){
                children.add(destinations.get(edge));
            }
        }
        for(Vertex child:children) {
        }
        System.out.println("fant children");
        return children;
    }

    // <3<3<3<3<3<3<3<3<3<3 reddet alt

    //opretter en lokal hashmap som inneholder alle sources og destinations til alle kantene, slik at man slipper å kalle på databasen hver gang

    public HashMap<Edge,Vertex> makeHashMapForEdgeSources(){
        HashMap<Edge, Vertex> s=new HashMap<>();
        for (Edge edge : Graph.getEdges()) {
            System.out.println(edge.getSource());
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

