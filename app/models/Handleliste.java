package models;

/**
 * Created by mathilde on 07/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Handleliste extends Model {

    private List<Vare> handleliste;

    public Handleliste(User user){
        
    }


}