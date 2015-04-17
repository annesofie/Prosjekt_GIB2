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
        List<Vertex>visited=new ArrayList<>();
        List<Vertex> fromRoot=new ArrayList<>();
        List<Vertex> fromDestination=new ArrayList<>();
        ArrayList<Vertex> path=new ArrayList<>();

        Vertex lastInRootPath=root;
        Vertex lastInDestinationPath=destination;

        while(visited.size()<targets.size()){
            Vertex nextR=closestTo(lastInRootPath);
            fromRoot.add(nextR);
            visited.add(nextR);
            lastInRootPath=nextR;
            if(visited.size()<targets.size()){
                break;
            }

            Vertex nextD=closestTo(lastInDestinationPath);
            fromDestination.add(nextD);
            visited.add(nextD);
            lastInDestinationPath=nextD;

        }

        path.add(root);
        path.addAll(fromRoot);
        Collections.reverse(fromDestination);
        path.addAll(fromDestination);
        path.add(destination);

        return path;
    }


    public Vertex closestTo(Vertex vertex){

        int w=99999;
        Vertex closest=null;

        for (weightedEdge edge : wGraph.edges){
            if (edge.getSource().equals(vertex)&& edge.weight<w){
                closest=edge.getDestination();
                w=edge.weight;
            }
            if(edge.getDestination().equals(vertex) && edge.weight<w){
                closest= edge.getSource();
                w=edge.weight;
            }
        }

        return (closest);

    }
}