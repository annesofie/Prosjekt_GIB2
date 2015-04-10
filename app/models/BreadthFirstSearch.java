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


    //Tar inn visited, for å kunne kalle på denne metoden og unngå at noder som er del av stien blir valgt flere ganger
    public String bfs(Vertex rootNode, ArrayList<Vertex> visited) {

        int hight=0;
        // BFS uses Queue data structure
        Queue queue = new LinkedList();
        queue.add(rootNode);
        visited.add(rootNode);
        while (!queue.isEmpty()) {
            Vertex vertex = (Vertex) queue.remove();
            Vertex child = null;
            hight+=1; //Teller nivåer i treet, altså totallengden til noden man sjekker
            while((child=getUnvisitedChildNodeInGraph(vertex))!=null){ //Går igjennom hvert barn
                if(targets.contains(child)){
                    return child+","+hight;
                }
                visited.add(child);
                queue.add(child);
            }
        }
        return null;

    }

    //Skal returnere en graf som viser vei fra alle target-noder til alle target-noder, et spenntre
    public weightedGraph bfsAllToAll() {

        // BFS uses Queue data structure
        visited=new ArrayList<>();
        Queue queue = new LinkedList();
        ArrayList<Vertex> graphVertices=new ArrayList<>();
        ArrayList<weightedEdge> graphEdges=new ArrayList<>();
        System.out.println("I bfsAllToAll - yey");

        for (Vertex rootNode:findTargets()){
            int hight = 0;
            queue.add(rootNode);
            System.out.println("queue i bfs " + queue);
            visited.add(rootNode);

            while (!(queue.isEmpty())) {
                System.out.println("i While i bfs");
                Vertex vertex = (Vertex)queue.remove();
                Vertex child = null;
                hight += 1; //Teller nivåer i treet, altså totallengden til noden man sjekker

                System.out.println("noden som blir sent til getChild er: "+vertex);
                while ((child = getUnvisitedChildNodeInGraph(vertex)) != null) { //Går igjennom hvert barn
                    System.out.println("i while i bfs");
                    if (targets.contains(child)) {
                        graphVertices.add(child);
                        System.out.println("child: "+child);
                        for (Edge edge : graph.edges) {
                            if (edge.getDestination().equals(child) && edge.getSource().equals(vertex)) {
                                graphEdges.add(new weightedEdge(vertex, child, hight));
                            }
                        }
                    }
                    visited.add(child);
                    queue.add(child);
                }
            }
        }

        return new weightedGraph(graphVertices,graphEdges);
    }


    public Vertex getUnvisitedChildNodeInGraph(Vertex vertex) {
        System.out.println("i getUnvisitetNodeInGraph");
        for (Vertex child:graph.getChildren(vertex)){
            if(!visited.contains(child)){
                return child;
            }
        }
        return null;
    }




}