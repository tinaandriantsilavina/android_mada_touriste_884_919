package com.madatouriste.modele;

import java.io.Serializable;
import java.util.ArrayList;

public class Province implements Serializable {
    private Integer id;
    private String nom, description, imageUrl;
    private ArrayList<String> images;

    public Province() {
    }

    public Province(String nom, String imageUrl) {
        this.id = 1;
        this.nom = nom;
        this.imageUrl = imageUrl;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
