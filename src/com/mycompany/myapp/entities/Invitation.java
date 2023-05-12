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
public class Invitation {
    
    private int id;
    private Contrat contrat;
    private String description;
    private String date_invitation;
    private User collecteur;
    private String statut_invitation;

    public Invitation(int id, Contrat contrat, String description, String date_invitation, User collecteur, String statut_invitation) {
        this.id = id;
        this.contrat = contrat;
        this.description = description;
        this.date_invitation = date_invitation;
        this.collecteur = collecteur;
        this.statut_invitation = statut_invitation;
    }

    public Invitation() {
    }

    public Invitation(int id, String description, String date_invitation, User collecteur, String statut_invitation) {
        this.id = id;
        this.description = description;
        this.date_invitation = date_invitation;
        this.collecteur = collecteur;
        this.statut_invitation = statut_invitation;
    }

    @Override
    public String toString() {
        return "Invitation{" + "id=" + id + ", contrat=" + contrat + ", description=" + description + ", date_invitation=" + date_invitation + ", collecteur=" + collecteur + ", statut_invitation=" + statut_invitation + '}';
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Contrat getContrat() {
        return contrat;
    }

    public void setContrat(Contrat contrat) {
        this.contrat = contrat;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate_invitation() {
        return date_invitation;
    }

    public void setDate_invitation(String date_invitation) {
        this.date_invitation = date_invitation;
    }

    public User getCollecteur() {
        return collecteur;
    }

    public void setCollecteur(User collecteur) {
        this.collecteur = collecteur;
    }

    public String getStatut_invitation() {
        return statut_invitation;
    }

    public void setStatut_invitation(String statut_invitation) {
        this.statut_invitation = statut_invitation;
    }
    
    
    
}
