/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;



/**
 *
 * @author amine
 */
public class Contrat {
    
    private int id;
    private String description;
    private String date_debut;
    private String date_fin;
    private String statut_contrat;
    private String type_contrat;
    private float montant;
    private Entreprise enterprise;
    private User collecteur;

    public Contrat() {
    }

    public Contrat(int id, String description, String date_debut, String date_fin, String statut_contrat, String type_contrat, float montant, Entreprise enterprise, User collecteur) {
        this.id = id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut_contrat = statut_contrat;
        this.type_contrat = type_contrat;
        this.montant = montant;
        this.enterprise = enterprise;
        this.collecteur = collecteur;
    }

    public Contrat(String description, String date_debut, String date_fin, String statut_contrat, String type_contrat, float montant, Entreprise enterprise, User collecteur) {
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut_contrat = statut_contrat;
        this.type_contrat = type_contrat;
        this.montant = montant;
        this.enterprise = enterprise;
        this.collecteur = collecteur;
    }

    public Contrat(int id, String description, String date_debut, String date_fin, String statut_contrat, String type_contrat, float montant, Entreprise enterprise) {
        this.id = id;
        this.description = description;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.statut_contrat = statut_contrat;
        this.type_contrat = type_contrat;
        this.montant = montant;
        this.enterprise = enterprise;
    }

    @Override
    public String toString() {
        return "Contrat{" + "id=" + id + ", description=" + description + ", date_debut=" + date_debut + ", date_fin=" + date_fin + ", statut_contrat=" + statut_contrat + ", type_contrat=" + type_contrat + ", montant=" + montant + ", enterprise=" + enterprise + ", collecteur=" + collecteur + '}';
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public String getStatut_contrat() {
        return statut_contrat;
    }

    public void setStatut_contrat(String statut_contrat) {
        this.statut_contrat = statut_contrat;
    }

    public String getType_contrat() {
        return type_contrat;
    }

    public void setType_contrat(String type_contrat) {
        this.type_contrat = type_contrat;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public Entreprise getEnterprise() {
        return enterprise;
    }

    public void setEnterprise(Entreprise enterprise) {
        this.enterprise = enterprise;
    }

    public User getCollecteur() {
        return collecteur;
    }

    public void setCollecteur(User collecteur) {
        this.collecteur = collecteur;
    }
    
    
    
    
}
