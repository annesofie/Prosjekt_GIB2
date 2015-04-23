package models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mathilde on 07/04/15.
 */

public class SortedShoppingList {



    public static List<Vare> sortShoppingList(String email) {
        User user = User.find.byId(email);
        Path path = new Path(user);
        List<Vare> sortedShoppingList = new ArrayList<>();
        List<Vare> shoppingList = new ArrayList<>();
        List<HandlelisteVare> handlelisteV = (HandlelisteVare.usersShoppingList(email));
        for(HandlelisteVare hv:handlelisteV){
            shoppingList.add(Vare.find.byId(hv.vareId));
        }

        System.out.println("Final path er: ");
        for(Vertex v:path.finalPath) {
            System.out.println(v.id);
        }

        for (Vertex vertex: path.finalPath) {
            for (Vare vare: shoppingList) {

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