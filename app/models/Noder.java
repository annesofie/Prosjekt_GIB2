package models;

/**
 * Created by mathilde on 09/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Noder extends Model {

    @Id
    public int nodenummer;
    public int positionX, positionY;


    public Noder(int node){
        nodenummer=node;
    }


}