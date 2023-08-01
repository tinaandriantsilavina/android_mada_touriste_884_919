package com.madatouriste.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Province implements Serializable {
    private String id;
    private String nom, description, pdp , pdc;
    private ArrayList<String> images, videos, liens;

    public Province() {
    }

    public Province(String nom, String pdp) {
        this.id = "1";
        this.nom = nom;
        this.pdp = pdp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public ArrayList<String> getImages() {
        return images;
    }

    public void setImages(ArrayList<String> images) {
        this.images = images;
    }

    public String getPdp() {
        return pdp;
    }

    public void setPdp(String pdp) {
        this.pdp = pdp;
    }

    public String getPdc() {
        return pdc;
    }

    public void setPdc(String pdc) {
        this.pdc = pdc;
    }

    public ArrayList<String> getVideos() {
        return videos;
    }

    public ArrayList<String> getLiens() {
        return liens;
    }

    public void setLiens(ArrayList<String> liens) {
        this.liens = liens;
    }

    public void setVideos(ArrayList<String> videos) {
        this.videos = videos;
    }
}
