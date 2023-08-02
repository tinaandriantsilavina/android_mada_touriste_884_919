package com.madatouriste.modele;

import java.io.Serializable;
import java.util.List;

public class Province implements Serializable {
    String _id;
    String nom;
    String description;
    String pdp;
    String pdc;
    List<String> liens;
    List<String> images;
    List<String> videos;

    public Province(String nom, String pdp) {
        this.nom = nom;
        this.pdp = pdp;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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

    public List<String> getLiens() {
        return liens;
    }

    public void setLiens(List<String> liens) {
        this.liens = liens;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }

    @Override
    public String toString() {
        return "Province{" +
                "_id='" + _id + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", pdp='" + pdp + '\'' +
                ", pdc='" + pdc + '\'' +
                ", liens=" + liens +
                ", images=" + images +
                ", videos=" + videos +
                '}';
    }
}
