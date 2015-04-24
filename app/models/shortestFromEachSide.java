package models;

import java.util.*;
/**
 * Created by mathilde on 17/04/15.
 */
public class shortestFromEachSide {

    public List<Vertex>targets;
    public weightedGraph wGraph;
    public Vertex root= Vertex.find.byId(1);
    public Vertex destination=Vertex.find.byId(18);
    public static ArrayList<Vertex>path;

    public shortestFromEachSide(List<Vertex> targets, weightedGraph wGraph ){

        this.targets=targets;
        this.wGraph=wGraph;
        this.path=path();
    }


    public ArrayList<Vertex> path(){
        ArrayList<Vertex>visited=new ArrayList<>();
        List<Vertex> fromRoot=new ArrayList<>();
        List<Vertex> fromDestination=new ArrayList<>();
        ArrayList<Vertex> path=new ArrayList<>();

        Vertex lastInRootPath=root;
        Vertex lastInDestinationPath=destination;
        visited.add(root);
        visited.add(destination);

        while((visited.size()-2) <targets.size()){

            Vertex nextR=closestTo(lastInRootPath,visited);
            fromRoot.add(nextR);
            visited.add(nextR);
            lastInRootPath=nextR;
            if((visited.size()-2)==targets.size()){
                break;
            }

            Vertex nextD=closestTo(lastInDestinationPath, visited);
            fromDestination.add(nextD);
            visited.add(nextD);
            lastInDestinationPath=nextD;

               }

        path.add(root);
        path.addAll(fromRoot);
        Collections.reverse(fromDestination);
        path.addAll(fromDestination);
        path.add(destination);

        int w=AllPermutations.getTotalWeightOfPath(path);

        return path;
    }


    public Vertex closestTo(Vertex vertex, ArrayList<Vertex> visited){

        int w=99999;
        Vertex closest=null;

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(vertex)&& edge.weight<w && (!(visited.contains(edge.getDestination())))){
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(vertex) && edge.weight<w && (!(visited.contains(edge.getSource())))){
                closest= edge.getSource();
                w=edge.weight;
            }

        }

        return (closest);

    }
}