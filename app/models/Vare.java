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


    public static Model.Finder<Long, Vare> find = new Model.Finder(Long.class, Vare.class);

    public Vare(String navn, String kategori, int x, int y, int z, double pris){
        this.navn = navn;
        this.kategori = kategori;
        this.pris = pris;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static List<Vare> vareFraKategori(String kategori){

        return find.where()
                .eq("kategori", kategori)
                .findList();
    }








}
