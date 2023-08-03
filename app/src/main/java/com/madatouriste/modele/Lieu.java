package com.madatouriste.modele;

import java.io.Serializable;
import java.util.List;

public class Lieu implements Serializable {
    String _id;
    String idprovince;
    String nom;
    String description;
    String pdp;
    String pdc;
    List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getIdprovince() {
        return idprovince;
    }

    public void setIdprovince(String idprovince) {
        this.idprovince = idprovince;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "Lieu{" +
                "_id='" + _id + '\'' +
                ", idprovince='" + idprovince + '\'' +
                ", nom='" + nom + '\'' +
                ", description='" + description + '\'' +
                ", pdp='" + pdp + '\'' +
                ", pdc='" + pdc + '\'' +
                ", images=" + images +
                '}';
    }
}
