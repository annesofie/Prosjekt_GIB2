package models;

/**
 * Created by mathilde on 11/03/15.
 */
import java.util.*;




public class Path{

    public Graph graph=new Graph();
    public DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
    public ArrayList<Vertex>targets;
    public BreadthFirstSearch bfs=new BreadthFirstSearch();



    public static void main (String[]args){
        System.out.println("Hei");
    }


    public ArrayList<Vertex> findTargets(){
        //Target er vertex'en nærmest varen man har lagret i handlelisten
        targets=new ArrayList<Vertex>();
        List<Vare> handle=User.handleliste;
        for (Vare vare:handle){
            targets.add(vare.vertex);
        }
        return targets;
    }

    //kjører prims-algoritme for å finne minimalt spenntre
    public void findMinimumSpanningTree(){

        import java.util.InputMismatchException;
        import java.util.Scanner;

        public class Prims
        {
            private boolean unsettled[];
            private boolean settled[];
            private int numberofvertices;
            private int adjacencyMatrix[][];
            private int key[];
            public static final int INFINITE = 999;
            private int parent[];

            public Prims(int numberofvertices)
            {
                this.numberofvertices = numberofvertices;
                unsettled = new boolean[numberofvertices + 1];
                settled = new boolean[numberofvertices + 1];
                adjacencyMatrix = new int[numberofvertices + 1][numberofvertices + 1];
                key = new int[numberofvertices + 1];
                parent = new int[numberofvertices + 1];
            }

            public int getUnsettledCount(boolean unsettled[])
            {
                int count = 0;
                for (int index = 0; index < unsettled.length; index++)
                {
                    if (unsettled[index])
                    {
                        count++;
                    }
                }
                return count;
            }

            public void primsAlgorithm(int adjacencyMatrix[][])
            {
                int evaluationVertex;
                for (int source = 1; source <= numberofvertices; source++)
                {
                    for (int destination = 1; destination <= numberofvertices; destination++)
                    {
                        this.adjacencyMatrix[source][destination] = adjacencyMatrix[source][destination];
                    }
                }

                for (int index = 1; index <= numberofvertices; index++)
                {
                    key[index] = INFINITE;
                }
                key[1] = 0;
                unsettled[1] = true;
                parent[1] = 1;

                while (getUnsettledCount(unsettled) != 0)
                {
                    evaluationVertex = getMimumKeyVertexFromUnsettled(unsettled);
                    unsettled[evaluationVertex] = false;
                    settled[evaluationVertex] = true;
                    evaluateNeighbours(evaluationVertex);
                }
            }

            private int getMimumKeyVertexFromUnsettled(boolean[] unsettled2)
            {
                int min = Integer.MAX_VALUE;
                int node = 0;
                for (int vertex = 1; vertex <= numberofvertices; vertex++)
                {
                    if (unsettled[vertex] == true && key[vertex] < min)
                    {
                        node = vertex;
                        min = key[vertex];
                    }
                }
                return node;
            }

            public void evaluateNeighbours(int evaluationVertex)
            {

                for (int destinationvertex = 1; destinationvertex <= numberofvertices; destinationvertex++)
                {
                    if (settled[destinationvertex] == false)
                    {
                        if (adjacencyMatrix[evaluationVertex][destinationvertex] != INFINITE)
                        {
                            if (adjacencyMatrix[evaluationVertex][destinationvertex] < key[destinationvertex])
                            {
                                key[destinationvertex] = adjacencyMatrix[evaluationVertex][destinationvertex];
                                parent[destinationvertex] = evaluationVertex;
                            }
                            unsettled[destinationvertex] = true;
                        }
                    }
                }
            }

            public void printMST()
            {
                System.out.println("SOURCE  : DESTINATION = WEIGHT");
                for (int vertex = 2; vertex <= numberofvertices; vertex++)
                {
                    System.out.println(parent[vertex] + "\t:\t" + vertex +"\t=\t"+ adjacencyMatrix[parent[vertex]][vertex]);
                }
            }

            public static void main(String... arg)
            {
                int adjacency_matrix[][];
                int number_of_vertices;
                Scanner scan = new Scanner(System.in);

                try
                {
                    System.out.println("Enter the number of vertices");
                    number_of_vertices = scan.nextInt();
                    adjacency_matrix = new int[number_of_vertices + 1][number_of_vertices + 1];

                    System.out.println("Enter the Weighted Matrix for the graph");
                    for (int i = 1; i <= number_of_vertices; i++)
                    {
                        for (int j = 1; j <= number_of_vertices; j++)
                        {
                            adjacency_matrix[i][j] = scan.nextInt();
                            if (i == j)
                            {
                                adjacency_matrix[i][j] = 0;
                                continue;
                            }
                            if (adjacency_matrix[i][j] == 0)
                            {
                                adjacency_matrix[i][j] = INFINITE;
                            }
                        }
                    }

                    Prims prims = new Prims(number_of_vertices);
                    prims.primsAlgorithm(adjacency_matrix);
                    prims.printMST();

                } catch (InputMismatchException inputMismatch)
                {
                    System.out.println("Wrong Input Format");
                }
                scan.close();
            }
        }

    }






  //ugått //Returnerer en array hvor hver rad inneholder en node og kantlengden fra forrige node i stien til noden
    public ArrayList<String> FindGreedyPath(){
        //finner en graadig sti, som kun velger noden med korteste avstand som enda ikke er besøkt
        Vertex rootVertex=graph.rootNode;
        ArrayList<String> path=new ArrayList<>();
        ArrayList<Vertex> delAvSti=new ArrayList<>();

        for(Vertex target:findTargets()){
            path.add(bfs.bfs(rootVertex,delAvSti));
            delAvSti.add(rootVertex);
            delAvSti.add(target);
            rootVertex=target;
        }

        return path;
    }

    public Integer getGreedyPathLength(ArrayList<String> path){
        int weight=0;
        for(String vertex:path){
            weight+=Integer.parseInt(vertex.split(",").toString());
        }
        return weight;
    }



}