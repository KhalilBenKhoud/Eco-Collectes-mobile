/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.*;
/**
 *
 * @author khalil
 */
public class UserService {
    
     public static UserService instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<User> user;
     private User loggedUser = new User();
     public boolean verf ; 
    public ArrayList<User> users;
    
    public UserService() {
         req = new ConnectionRequest();
    }

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

        public ArrayList<User> parseUser(String jsonText){
                    ArrayList<User> userr = new ArrayList<>();

        try {
            
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
           
            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");

            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                User a = new User();
                         System.out.println("-------------");
                              System.out.println(obj.get("username"));
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setUsername(obj.get("username").toString());
                a.setEmail( obj.get("email").toString());
//                a.setRole(obj.get("role").toString());
                a.setPassword(obj.get("password").toString());
                a.setPhone(Integer.parseInt(obj.get("phone").toString()));
//                a.setIdEquipe((int)obj.get("idEquipe"));
               
               
                //Ajouter la tâche extraite de la réponse Json à la liste
                userr.add(a);
            }
            
            
        } catch (Exception ex) {
             ex.printStackTrace();
        }
         /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
        */


        return userr;
    }

        
                public boolean addUser(User a) {
        String url = Statics.BASE_URL + "registerApi?username=" + a.getUsername() +  "&email=" + a.getEmail() +"&password=" + a.getPassword() +"&phone=" + a.getPhone() +  "&address=" + a.getAddress() +  "&gender=" + a.getGender()  ; //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); 
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
      return resultOK;
    } 
                
         public boolean  Delete1 (int id){
       String url = Statics.BASE_URL + "deleteApi/" +id ;

        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                req.removeResponseListener(this);
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
      
      
      }
         
              public boolean modifier(User a) {
        String url = Statics.BASE_URL + "updateApi/" + a.getId()+ "?username=" + a.getUsername() +  "&email=" + a.getEmail() +"&password=" + a.getPassword() +"&phone=" + a.getPhone() +  "&address=" + a.getAddress() +  "&gender=" + a.getGender()   ; //création de l'URL
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               resultOK = req.getResponseCode() == 200; // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);
        boolean resultOK = false;
    return resultOK;
        
    }
    
              
       public ArrayList<User> getAllUser(){
         String url =  Statics.BASE_URL + "showAllApi";
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
           System.out.println("req");
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                user = parseUser( new String(req.getResponseData()));
                System.out.println("user:"  + user );
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return user;
    }
   

}
