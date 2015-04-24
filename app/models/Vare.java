package models;

/**
 * Created by annesofiestranderichsen on 03.03.15.
 */
import play.db.ebean.*;
import javax.persistence.*;
import java.util.*;

@Entity
public class Vare extends Model {

    @Id
    public Long vareid;
    public String navn;
    public String kategori;
    public int x, y, z;
    public double pris;
    public Vertex vertex;
    public String pic;
    public Integer vertexId;
    public boolean in_shoppinglist;


    public static Model.Finder<Long,Vare> find = new Model.Finder(Long.class, Vare.class);

    public Vare(String navn, String kategori, double pris, Integer vertexId, Integer x, Integer y, Integer z, String pic){
        this.navn = navn;
        this.kategori = kategori;
        this.pris = pris;
        this.x = x;
        this.y = y;
        this.z = z;
        this.vertexId=vertexId;
        this.pic = pic;
        this.in_shoppinglist = false;
    }

    public Vertex findVareVertex(){
        return Vertex.find.byId(this.vertexId);
    }


}
