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

    public Province(){

    }
    public Province(String _id, String nom, String description, String pdp, String pdc, List<String> liens, List<String> images, List<String> videos) {
        this._id = _id;
        this.nom = nom;
        this.description = description;
        this.pdp = pdp;
        this.pdc = pdc;
        this.liens = liens;
        this.images = images;
        this.videos = videos;
    }

    public Province(String nom, String pdp) {
        this.nom = nom;
        this.pdp = pdp;
    }

    public String get_id() {
        return _id;
    }

    public Province set_id(String _id) {
        this._id = _id;
        return this;
    }

    public String getNom() {
        return nom;
    }

    public Province setNom(String nom) {
        this.nom = nom;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Province setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getPdp() {
        return pdp;
    }

    public Province setPdp(String pdp) {
        this.pdp = pdp;
        return this;
    }

    public String getPdc() {
        return pdc;
    }

    public Province setPdc(String pdc) {
        this.pdc = pdc;
        return this;
    }

    public List<String> getLiens() {
        return liens;
    }

    public Province setLiens(List<String> liens) {
        this.liens = liens;
        return this;
    }

    public List<String> getImages() {
        return images;
    }

    public Province setImages(List<String> images) {
        this.images = images;
        return this;
    }

    public List<String> getVideos() {
        return videos;

    }

    public Province setVideos(List<String> videos) {
        this.videos = videos;
        return this;
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
