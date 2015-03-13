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

    public ArrayList<Vertex> findTargets(String email){
        //Target er vertex'en nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();
        List<Vare> handle=User.handleliste;
        for (Vare vare:handle){
            targets.add(vare.vertex);
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
            while((child=getUnvisitedChildNode(vertex))!=null){ //Går igjennom hvert barn
                if(targets.contains(child)){
                    return child+","+hight;
                }
                visited.add(child);
                queue.add(child);
            }
        }
        return null;

    }


    public Vertex getUnvisitedChildNode(Vertex vertex) {
        for (Vertex child:getChildren(vertex)){
            if(!visited.contains(child)){
                return child;
            }
        }
        return null;
    }


    //Finner nabonodene til en gitt node
    public List<Vertex> getChildren(Vertex node) {
        List<Vertex> children = new ArrayList<Vertex>();
        for (Edge edge : graph.edges) {
            if (edge.getSource().equals(node)) {
                children.add(edge.getDestination());
            }
        }
        return children;
    }

}