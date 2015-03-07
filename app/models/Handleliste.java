package models;

/**
 * Created by mathilde on 07/03/15.
 */

import play.db.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Handleliste extends Model {

    @Id
    private String email;
    private List<Vare> handleliste;

    public static Finder<String,User> find = new Model.Finder(String.class, User.class);




}