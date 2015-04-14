package models;
import java.util.*;

/**
 * Created by mathilde on 14/03/15.
 */
public class weightedEdge {


        //Vi må huske å lagre to kanter mellom nodene, fordi dijsktra kjører på directed graphs, og vi oonsker at man skal kunne gå begge retninger


        public Vertex source;
        public Vertex destination;
        public int weight;
        public ArrayList<Vertex> visitedVertices;




        public weightedEdge(Vertex source, Vertex destination, Integer weight, ArrayList<Vertex>visitedVertices) {

            this.source = source;
            this.destination = destination;
            this.weight=weight;
            this.visitedVertices=visitedVertices;

        }

        public Vertex getDestination() {
            return destination;
        }

        public Vertex getSource() {
            return source;
        }



        @Override
        public String toString() {
            return source + " " + destination;
        }
    }