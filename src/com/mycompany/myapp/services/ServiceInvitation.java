/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Invitation;
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author hamza
 */
public class ServiceInvitation {
    
    //singleton 
    public static ServiceInvitation instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    public ArrayList<User> tasks;
    public ArrayList<Invitation> invitationtasks;
    public LinkedHashMap<String, Object> collecteur = new LinkedHashMap<>();
    public LinkedHashMap<String, Object> contrat = new LinkedHashMap<>();

    public static ServiceInvitation getInstance() {
        if (instance == null) {
            instance = new ServiceInvitation();
        }
        return instance;
    }

    public ServiceInvitation() {
        req = new ConnectionRequest();
    }

    //ajout 
    public void ajoutInvitation(Invitation invitation,int id_user) {

        String url = Statics.BASE_URL + "api/addInvitationJSON/new/"+id_user+"?description=" + invitation.getDescription() + "&collecteur=" + invitation.getCollecteur().getId() + "&contrat=" + invitation.getContrat().getId();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Invitation> parseInvitations(String jsonText) {
        try {
            invitationtasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Invitation i = new Invitation();

                float id = Float.parseFloat(obj.get("id").toString());
                String description = obj.get("description").toString();
                String dateInvitation = obj.get("dateInvitation").toString().substring(0, obj.get("dateInvitation").toString().indexOf('T'));
                String statutInvitation = obj.get("statutInvitation").toString();

                //////////////Contrat////////////////
                contrat = (LinkedHashMap) obj.get("contrat");
                System.out.println(collecteur);
                Contrat c = new Contrat();

                    for (Map.Entry<String, Object> entry : collecteur.entrySet()) {
                    if (entry.getKey().contains("description")) {
                        c.setDescription(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("dateDebut")) {
                        c.setDate_debut(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("dateFin")) {
                        c.setDate_fin(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("statutContrat")) {
                        c.setStatut_contrat(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("typeContrat")) {
                        c.setType_contrat(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("montant")) {
                        c.setMontant(Float.parseFloat(entry.getValue().toString()));
                    }
                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
                        //    System.out.println(pp);
                        int idd = (int) Float.parseFloat(pp);
                        c.setId((int) idd);
                    }
                
                }
                ///////////////End Collecteur///////////////
                //////////////Collecteur////////////////
                collecteur = (LinkedHashMap) obj.get("collecteur");
                System.out.println(collecteur);
                User u = new User();
                if(collecteur!=null){
                    for (Map.Entry<String, Object> entry : collecteur.entrySet()) {
                    if (entry.getKey().contains("address")) {
                        u.setAddress(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("phone")) {
                        u.setPhone(Integer.parseInt(entry.getValue().toString()));
                    }
                    if (entry.getKey().contains("username")) {
                        u.setUsername(entry.getValue().toString());
                    }
                    if (entry.getKey().contains("email")) {
                        u.setEmail(entry.getValue().toString());
                    }
                 
                    if (entry.getKey().contains("gender")) {
                        u.setGender(entry.getValue().toString());
                    }

                    if (entry.getKey().contains("id")) {
                        String pp = entry.getValue().toString();
                        //    System.out.println(pp);
                        int idd = (int) Float.parseFloat(pp);
                        u.setId((int) idd);
                    }
                }
                }
                ///////////////End Collecteur///////////////
                i.setId((int) id);
                i.setDescription(description);
                i.setDate_invitation(dateInvitation);
                i.setStatut_invitation(statutInvitation);
                i.setCollecteur(u);
                i.setContrat(c);
               
                invitationtasks.add(i);
            }
        } catch (IOException ex) {

        }
        return invitationtasks;
    }

    public ArrayList<Invitation> affichageInvitations(int id_user) {
        ArrayList<Invitation> result = new ArrayList<>();
        String url = Statics.BASE_URL + "api/allinvitationsjson/" + id_user;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        System.out.println(req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                invitationtasks = parseInvitations(new String(req.getResponseData()));
                System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return invitationtasks;
    }

    //Delete 
    public boolean deleteInvitation(int id) {
        String url = Statics.BASE_URL + "api/deleteInvitationJSON/" + id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOk;
    }

  
    
    
}
