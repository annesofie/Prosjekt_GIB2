package models;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by mathilde on 07/04/15.
 */

public class SortedShoppingList {

    public static List<Vare> sortShoppingList() {
        Path path = new Path(Vertex.find.byId(0),Vertex.find.byId(100)); //legg inn rotnodenummer og kassenodenummer i input
        List<Vertex>p=path.finalPath;
        List<Vare> sortedShoppingList = new ArrayList<Vare>();
        for (Vertex vertex: p) {
            for (Vare vare: User.handleliste) {
                if (vare.vertex == vertex) {
                    sortedShoppingList.add(vare);
                }
            }
        }

        return sortedShoppingList;
    }


}