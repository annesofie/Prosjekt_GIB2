package models;
import java.util.*;

/**
 * Created by mathilde on 14/03/15.
 */
public class weightedEdge {


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