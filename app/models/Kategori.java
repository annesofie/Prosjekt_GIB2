package models;

import play.db.ebean.*;
import javax.persistence.*;


@Entity
public class Kategori extends Model{

    @Id
    public String kategoriNavn;
    public int closestNode;


    public static Model.Finder<Long, Vare> find = new Model.Finder(Long.class, Vare.class);

    public Kategori(String navn, int node){
        this.kategoriNavn=navn;
        this.closestNode=node;
    }

}
