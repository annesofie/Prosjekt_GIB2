package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class DijkstraAlgorithmTgaar {

    private final List<Vertex> nodes;
    private final List<Edge> edges;
    private Set<Vertex> settledNodes;
    private Set<Vertex> unSettledNodes;
    private Map<Vertex, Vertex> predecessors;
    private Map<Vertex, Integer> distance;

    Graph graph = new Graph();

    public DijkstraAlgorithmTgaar(Graph graph) {
        // create a copy of the array so that we can operate on this array
        this.nodes = new ArrayList<Vertex>(graph.vertexes);
        this.edges = new ArrayList<Edge>(graph.edges);
    }

    /*public void execute(Vertex startPoint) {
        settledNodes = new HashSet<Vertex>();
        unSettledNodes = new HashSet<Vertex>();
        distance = new HashMap<Vertex, Integer>();
        predecessors = new HashMap<Vertex, Vertex>();
        distance.put(startPoint, 0);
        unSettledNodes.add(startPoint);

        while (unSettledNodes.size() > 0) {
            Vertex node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
    }*/

    private Vertex getMinimum(Set<Vertex> vertexes) {
        Vertex minimum = null;
        for (Vertex vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    private int getShortestDistance(Vertex destination) {
        Integer d = distance.get(destination);
        if (d == null) {
            return Integer.MAX_VALUE;
        } else {
            return d;
        }
    }

    //Finner nabonodene til noden, for alle naboer sjekker om
    //public void findMinimalDistances(Vertex node) {
    //    List<Vertex> adjacentNodes = getNeighbors(node);
    //      for (Vertex target : adjacentNodes) {
    //      if (getShortestDistance(target) > getShortestDistance(node) + getDistance(node, target)) {
    /*            distance.put(target, getShortestDistance(node) + getDistance(node, target));
                predecessors.put(target, node);
                unSettledNodes.add(target);
            }
        }
    }*/

    //Finner kantverdien mellom to noder
    //private int getDistance(Vertex node, Vertex target) {
    //    for (Edge edge : edges) {
    //        if (edge.getSource().equals(node)
    //                && edge.getDestination().equals(target)) {
    //            return edge.getWeight();
    //        }
    //    }
    //    throw new RuntimeException("Should not happen");
    //}

    //Finner nabonodene til en gitt node
    public List<Vertex> getNeighbors(Vertex node) {
        List<Vertex> neighbors = new ArrayList<Vertex>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(node)
                   ) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }



    private boolean isSettled(Vertex vertex) {
        return settledNodes.contains(vertex);
    }



    /*
     * This method returns the path from the source to the selected target and
     * NULL if no path exists
     */
    public LinkedList<Vertex> getPath(Vertex target) {
        LinkedList<Vertex> path = new LinkedList<Vertex>();
        Vertex step = target;
        // check if a path exists
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }

        // Put it into the correct order
        Collections.reverse(path);
        return path;
    }

    public ArrayList<ArrayList<Vertex>> getPathToAllRelevantNodes(ArrayList<Vertex> targets) {

        ArrayList<Vertex> path;
        ArrayList<ArrayList<Vertex>> paths = new ArrayList<ArrayList<Vertex>>();
        int pathWeight=0;

        for (Vertex target : targets) {
            path = new ArrayList<Vertex>();
            Vertex step = target;
            // check if a path exists
            if (predecessors.get(step) == null) {
                return null;
            }
            path.add(step);
            while (predecessors.get(step) != null) {
                step = predecessors.get(step);
                path.add(step);
            }
            // Put it into the correct order
            Collections.reverse(path);
            paths.add(path);

        }
            return paths;

    }

}
