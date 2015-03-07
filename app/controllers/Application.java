package controllers;

import models.Vare;

import play.*;
import play.api.data.Form;
import play.mvc.*;

import views.html.*;

import java.util.ArrayList;
import java.util.List;


public class Application extends Controller {

    public static Result index() {

        List<Vare> alleVarer = Vare.find.all();
        List<Vare> elektro = new ArrayList<>();
        List<Vare> fritid = new ArrayList<>();
        List<Vare> jernvare = new ArrayList<>();
        List<Vare> multimedia = new ArrayList<>();
        List<Vare> hjem = new ArrayList<>();

        for(Vare vare:alleVarer){

            if(vare.kategori.equals("elektro")){
                elektro.add(vare);
            }
            if(vare.kategori.equals("fritid")){
                fritid.add(vare);
            }
            if(vare.kategori.equals("jernvare")){
                jernvare.add(vare);
            }
            if(vare.kategori.equals("multimedia")){
                multimedia.add(vare);
            }
            if(vare.kategori.equals("hjem")){
                hjem.add(vare);
            }

        }
        return ok(index.render("Finn den varen", alleVarer, elektro, fritid, hjem, jernvare, multimedia));
    }


}
