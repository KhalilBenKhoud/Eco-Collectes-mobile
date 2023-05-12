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
public class User {
    
    private int id;
    private String username;
    private String email;
    private int phone;
    private String address;
    private String[] roles;
    private String password;
    private String imageFilename;
    private String gender;

    public User(int id, String username, String email, int phone, String address, String[] roles, String password, String imageFilename, String gender) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
        this.password = password;
        this.imageFilename = imageFilename;
        this.gender = gender;
    }

    public User() {
    }

    public User(String username, String email, int phone, String address, String[] roles, String password, String imageFilename, String gender) {
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.roles = roles;
        this.password = password;
        this.imageFilename = imageFilename;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return username;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getImageFilename() {
        return imageFilename;
    }

    public void setImageFilename(String imageFilename) {
        this.imageFilename = imageFilename;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    
    
    
    
}
