package models;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mathilde on 07/04/15.
 */

public class SortedShoppingList {



    public static List<Vare> sortShoppingList() {
        Path path = new Path(Vertex.find.byId(1),Vertex.find.byId(18)); //legg inn rotnodenummer og kassenodenummer i input
        List<Vare> sortedShoppingList = new ArrayList<>();

        System.out.println("Final path er: ");
        for(Vertex v:path.finalPath) {
            System.out.println(v.id);
        }

        for (Vertex vertex: path.finalPath) {
            for (Vare vare: User.handleliste) {

                if (vare.findVareVertex().equals(vertex)) {
                    sortedShoppingList.add(vare);
                }
            }
        }
        System.out.println("Varer i handlelisten er: ");
        for(Vare v:sortedShoppingList) {
            System.out.println(v.navn);
        }

        return sortedShoppingList;
    }


}