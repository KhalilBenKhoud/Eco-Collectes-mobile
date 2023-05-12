/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author khalil
 */
public class User {
    
    private int id, phone ;
    private String email, username, address, password,  gender ;

   

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public User() {
        
    }
    public User( int phone, String email, String username, String address, String password, String gender) {
       
        this.phone = phone;
        this.email = email;
        this.username = username;
        this.address = address;
        this.password = password;
        this.gender = gender;
    }

    public void setId(int id) {
        this.id = id;
    }
  
    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPassword(String password) {
        this.password = password;
    }

  

    public int getId() {
        return id;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

 
    
}
