package models;

import java.util.ArrayList;
import java.util.List;
import play.db.ebean.*;
import javax.persistence.*;

/**
 * Created by mathilde on 07/04/15.
 */

@Entity
public class SortedShoppingList {

    @Id
    public Integer henteNr;
    public Long vareId;


    public SortedShoppingList(int nr, long id){
        this.henteNr=nr;
        this.vareId=id;

    }



    public static List<Vare> sortShoppingList() {
        Path path = new Path(Vertex.find.byId(1),Vertex.find.byId(100)); //legg inn rotnodenummer og kassenodenummer i input
        List<Vertex>p=path.finalPath;
        List<Vare> sortedShoppingList = new ArrayList<Vare>();
        int count=0;
        for (Vertex vertex: p) {
            for (Vare vare: User.handleliste) {
                if (vare.vertex == vertex) {
                    sortedShoppingList.add(vare);
                    count=count+1;
                    SortedShoppingList list=new SortedShoppingList(count, vare.vareid);
                    list.save();
                }
            }
        }

        return sortedShoppingList;
    }


    public void empty(){

    }



}