package models;

import java.util.*;
/**
 * Created by mathilde on 16/04/15.
 */
public class AllPermutations {

    public ArrayList<Vertex> used;
    public List<List<Vertex>> allPermutations;
    public List<Vertex> in;
    public List<Vertex> out;
    public int size=0;
    public int INFINITE=999999;
    public Vertex root;
    public Vertex end;
    public weightedGraph wGraph;


    int i;

    public AllPermutations(List<Vertex> vertices, Vertex root, Vertex end, weightedGraph weightedG){
        in=vertices;
        i=0;
        used=new ArrayList();
        out=new ArrayList();
        this.root=root;
        this.end=end;
        this.wGraph=weightedG;

        allPermutations=generatePerm(vertices);



    }

    public List<List<Vertex>> generatePerm(List<Vertex> original) {
        if (original.size() == 0) {
            List<List<Vertex>> result = new ArrayList<>();
            result.add(new ArrayList<>());
            return result;
        }
        Vertex firstElement = original.remove(0);
        List<List<Vertex>> returnValue = new ArrayList<>();
        List<List<Vertex>> permutations = generatePerm(original);
        for (List<Vertex> smallerPermutated : permutations) {
            for (int index=0; index <= smallerPermutated.size(); index++) {
                List<Vertex> temp = new ArrayList<>(smallerPermutated);
                temp.add(index, firstElement);
                returnValue.add(temp);
            }
        }
        return returnValue;
    }


    public List<Vertex> getBestPath(Integer big){
        List<Vertex> bestPath=new ArrayList<>();
        int weight=10000;
        for(List<Vertex> l:allPermutations){
            List<Vertex> list=new ArrayList<>();
            if(big==1){
                list.add(root);
                list.addAll(Path.closeToStart);
                list.addAll(l);
                list.add(Path.closeToEnd.get(0));
                list.add(Path.closeToEnd.get(1));
                list.add(end);
            }

            else if(big==0){
                list.add(root);
                list.addAll(l);
                list.add(end);
            }
            int totw=getTotalWeightOfPath(list);
            if(totw<weight){
                weight=totw;
                bestPath=list;
            }
        }
        return bestPath;
    }

    public int getTotalWeightOfPath(List<Vertex> list){
        int totW=0;
        for(int i=0; i<list.size()-1;i++){
            for (weightedEdge e : wGraph.edges) {
                if (((e.getDestination().equals(list.get(i))) && (e.getSource().equals(list.get(i+1))))||(e.getDestination().equals(list.get(i+1)) && (e.getSource().equals(list.get(i))))){
                    totW=totW+e.weight;
                }
            }
        }
        System.out.println("totalvekt er "+totW);
        return totW;
    }

    public Vertex closestTo(Vertex vertex){

        int w=INFINITE;
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
