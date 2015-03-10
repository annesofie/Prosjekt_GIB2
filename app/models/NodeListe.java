package models;

/**
 * Created by mathilde on 09/03/15.
 */

import play.db.ebean.Model;

import java.util.*;
public class NodeListe {



    public List<Node> linkedListNodes;

    public static Model.Finder<Integer, Node> find = new Model.Finder(Integer.class, Node.class);

    public NodeListe(){
       linkedListNodes.addAll(getAllNoder());
    }

    public List<Node> getAllNoder(){
        return Node.find.all();

    }




}