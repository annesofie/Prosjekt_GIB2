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


    public List<Vertex> getBestPath(){
        List<Vertex> bestPath=new ArrayList<>();
        int weight=10000;
        for(List<Vertex> l:allPermutations){
            List<Vertex> list=new ArrayList<>();
            list.add(root);
            list.addAll(l);
            list.add(end);
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

}
