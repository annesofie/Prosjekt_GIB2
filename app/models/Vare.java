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


    public static Model.Finder<Integer, Vertex> find = new Model.Finder(Integer.class, Vertex.class);

    public static Model.Finder<Long, Vare> findVare = new Model.Finder(Long.class, Vare.class);

    public Vare(String navn, String kategori, int x, int y, int z, double pris, Integer vertexId, String pic){
        this.navn = navn;
        this.kategori = kategori;
        this.pris = pris;
        this.x = x;
        this.y = y;
        this.z = z;
        this.vertex=find.byId(vertexId);
        this.pic = pic;
    }

    public static List<Vare> vareFraKategori(String kategori){

        return findVare.where()
                .eq("kategori", kategori)
                .findList();
    }


}
