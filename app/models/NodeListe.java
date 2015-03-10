package models;

/**
 * Created by mathilde on 09/03/15.
 */

import play.db.ebean.Model;

import java.util.*;
public class NodeListe {



    public List<NodeListe> linkedListNodes;

    public static Model.Finder<Integer, NodeListe> find = new Model.Finder(Integer.class, NodeListe.class);

    public NodeListe(){
       linkedListNodes.addAll(getAllNoder());
    }

    public List<NodeListe> getAllNoder(){
        return NodeListe.find.all();

    }




}