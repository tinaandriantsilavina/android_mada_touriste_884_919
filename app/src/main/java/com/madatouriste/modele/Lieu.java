package com.madatouriste.modele;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Lieu {
    private Integer id , idProvince;
    private String nom , description;
    private ArrayList<String> liens,  images ;

    public Lieu(){

    }
    public Lieu(Integer id, Integer idP , String nom, String description, ArrayList<String> l, ArrayList<String> i){
        this.id =id;
        this.idProvince = idP;
        this.nom = nom ;
        this.description = description;
        this.liens = l;
        this.images =i;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdProvince() {
        return idProvince;
    }

    public void setIdProvince(Integer idProvince) {
        this.idProvince = idProvince;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<String> getLiens() {
        return liens;
    }

    public void setLiens(ArrayList<String> liens) {
        this.liens = liens;
    }

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }
}
