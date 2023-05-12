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
import com.mycompany.myapp.entities.Annonce;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author GAMERS
 */
public class ServiceAnnonce {

    ConnectionRequest req;
    List<Annonce> annonces = new ArrayList<>();
    public boolean resultOK;

    public ServiceAnnonce() {
        req = new ConnectionRequest();
    }

    public Annonce getOneById(int id) {
        String url = Statics.BASE_URL + "showAnnonce/" + id;
        req.setUrl(url);
        Annonce a = new Annonce();
        req.addResponseListener(((evt) -> {
            String str = new String(req.getResponseData());
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                a.setTitre(obj.get("titre").toString());
                a.setDescAnnonce(obj.get("description").toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
                a.setDateCreation(dateFormat.parse((String) obj.get("dateCreation")));
                a.setDateModification(dateFormat.parse((String) obj.get("dateModification")));

            } catch (Exception ex) {
                ex.printStackTrace();
            }
            System.out.println("error in" + str);
        }));
        NetworkManager.getInstance().addToQueueAndWait(req);
        return a;
    }

    public boolean addAnnonce(String titre, String desc) {
        String url = Statics.BASE_URL + "ajouterAnnonce?titre=" + titre + "&desc=" + desc; //création de l'URL
        req.setUrl(url);
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

    public List<Annonce> getAll() {
        List<Annonce> annonces = new ArrayList<>();

        String fetchUrl = Statics.BASE_URL + "/showAllAnnonces";

        req.setUrl(fetchUrl);

        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                req.removeResponseListener(this);
            }
        }
        );

        NetworkManager.getInstance().addToQueueAndWait(req);
        return annonces;
    }

    public List<Annonce> parseAnnonces(String jsonText) {
        try {
            ArrayList<Object> Annonces = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ClientListJson.get("root");

            for (Map<String, Object> obj : list) {

                Annonce a = new Annonce();
                int id = Integer.parseInt(obj.get("id").toString());

                a.setId((int) id);
                a.setTitre(obj.get("titre").toString());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
                a.setDateCreation(dateFormat.parse((String) obj.get("dateCreation")));
                a.setDateModification(dateFormat.parse((String) obj.get("dateModification")));

                annonces.add(a);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return annonces;
    }

    public void supprimer(int id) {

    }

}
