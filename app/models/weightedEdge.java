package models;

/**
 * Created by mathilde on 14/03/15.
 */
public class weightedEdge {


        //Vi må huske å lagre to kanter mellom nodene, fordi dijsktra kjører på directed graphs, og vi oonsker at man skal kunne gå begge retninger


        public final Vertex source;
        public final Vertex destination;
        public final int weight;




        public weightedEdge(Vertex source, Vertex destination, Integer weight) {

            this.source = source;
            this.destination = destination;
            this.weight=weight;
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