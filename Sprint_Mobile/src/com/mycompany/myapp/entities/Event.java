/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;


/**
 *
 * @author Hamza
 */
public class Event {
    private int id;
    private String title;
    private String description;
    private String datedebut;
    private String datefin;

    public Event(int id, String title, String description, String datedebut, String datefin) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
public Event( String title, String description, String datedebut, String datefin) {
        this.title = title;
        this.description = description;
        this.datedebut = datedebut;
        this.datefin = datefin;
    }
    public Event() {
 //To change body of generated methods, choose Tools | Templates.
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatedebut() {
        return datedebut;
    }

    public void setDatedebut(String datedebut) {
        this.datedebut = datedebut;
    }

    public String getDatefin() {
        return datefin;
    }

    public void setDatefin(String datefin) {
        this.datefin = datefin;
    }

   
   
   
}
