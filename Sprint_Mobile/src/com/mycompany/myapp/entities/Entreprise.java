/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author amine
 */
public class Entreprise {
    
    private int id;
    private String nom;
    private String adresse;
    private int numtel;
    private Date date_creation;
    private String adresse_email;
    private String type_enterprise;
    private User ceo;

    public Entreprise() {
    }

    public Entreprise(int id, String nom, String adresse, int numtel, Date date_creation, String adresse_email, String type_enterprise, User ceo) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.date_creation = date_creation;
        this.adresse_email = adresse_email;
        this.type_enterprise = type_enterprise;
        this.ceo = ceo;
    }

    public Entreprise(String nom, String adresse, int numtel, Date date_creation, String adresse_email, String type_enterprise, User ceo) {
        this.nom = nom;
        this.adresse = adresse;
        this.numtel = numtel;
        this.date_creation = date_creation;
        this.adresse_email = adresse_email;
        this.type_enterprise = type_enterprise;
        this.ceo = ceo;
    }
    
 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public Date getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Date date_creation) {
        this.date_creation = date_creation;
    }

    public String getAdresse_email() {
        return adresse_email;
    }

    public void setAdresse_email(String adresse_email) {
        this.adresse_email = adresse_email;
    }

    public String getType_enterprise() {
        return type_enterprise;
    }

    public void setType_enterprise(String type_enterprise) {
        this.type_enterprise = type_enterprise;
    }

    public User getCeo() {
        return ceo;
    }

    public void setCeo(User ceo) {
        this.ceo = ceo;
    }

    @Override
    public String toString() {
        return "Entreprise{" + "id=" + id + ", nom=" + nom + ", adresse=" + adresse + ", numtel=" + numtel + ", date_creation=" + date_creation + ", adresse_email=" + adresse_email + ", type_enterprise=" + type_enterprise + ", ceo=" + ceo + '}';
    }
    
    
    
}
