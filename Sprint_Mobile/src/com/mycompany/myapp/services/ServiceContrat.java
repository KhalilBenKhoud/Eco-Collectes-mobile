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
import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 *
 * @author amine
 */
public class ServiceContrat {

    //singleton 
    public static ServiceContrat instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    public ArrayList<User> tasks;
    public ArrayList<Contrat> contrattasks;
    public LinkedHashMap<String, Object> collecteur = new LinkedHashMap<>();
    private final static String ACCOUNT_SID = "ACfdbf8273d65abad49c960b20f56a96a5";
    private final static String AUTH_TOKEN = "db1d3098ec890a9b91e08dd65c12abb3";

    public static ServiceContrat getInstance() {
        if (instance == null) {
            instance = new ServiceContrat();
        }
        return instance;
    }

    public ServiceContrat() {
        req = new ConnectionRequest();
    }

    //ajout 
    //http://localhost:8000/api/addContratJSON/new?Description=test&DateDebut=2023-02-24&DateFin=2023-02-24&TypeContrat=cdi&Montant=500
    public void ajoutContrat(Contrat contrat, int id_user) {

        String url = Statics.BASE_URL + "api/addContratJSON/new/" + id_user + "?Description=" + contrat.getDescription() + "&DateDebut=" + contrat.getDate_debut() + "&DateFin=" + contrat.getDate_fin() + "&TypeContrat=" + contrat.getType_contrat() + "&Montant=" + contrat.getMontant();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }

    public ArrayList<Contrat> parseContrats(String jsonText) {
        try {
            contrattasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Contrat e = new Contrat();

                float id = Float.parseFloat(obj.get("id").toString());
                String description = obj.get("description").toString();
                String date_debut = obj.get("dateDebut").toString().substring(0, obj.get("dateDebut").toString().indexOf('T'));
                String date_fin = obj.get("dateFin").toString().substring(0, obj.get("dateFin").toString().indexOf('T'));
                String statut_contrat = obj.get("statutContrat").toString();
                String type_contrat = obj.get("typeContrat").toString();
                float montant = Float.parseFloat(obj.get("montant").toString());

                //////////////Collecteur////////////////
                collecteur = (LinkedHashMap) obj.get("collecteur");
                System.out.println(collecteur);
                User u = new User();
                if (collecteur != null) {
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
                        if (entry.getKey().contains("imageFilename")) {
                            u.setImageFilename(entry.getValue().toString());
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
                e.setId((int) id);
                e.setDescription(description);
                e.setDate_debut(date_debut);
                e.setDate_fin(date_fin);
                e.setType_contrat(type_contrat);
                e.setMontant((int) montant);
                e.setStatut_contrat(statut_contrat);
                e.setCollecteur(u);
                contrattasks.add(e);
            }
        } catch (IOException ex) {

        }
        return contrattasks;
    }

    public ArrayList<User> parseCollecteurs(String jsonText) {
        try {
            tasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                User u = new User();
                float id = Float.parseFloat(obj.get("id").toString());
                String email = obj.get("email").toString();
                String address = obj.get("address").toString();
                String phone = obj.get("phone").toString();
                String username = obj.get("username").toString();
                String password = obj.get("password").toString();
                String gender = obj.get("gender").toString();

                u.setId((int) id);
                u.setEmail(email);
                u.setAddress(address);
                u.setPhone(Integer.parseInt(phone));
                u.setUsername(username);
                u.setGender(gender);
                u.setPassword(password);
                tasks.add(u);
            }
        } catch (IOException ex) {

        }
        return tasks;
    }

    public ArrayList<User> affichageCollecteurs() {
        ArrayList<User> result = new ArrayList<>();

        String url = Statics.BASE_URL + "api/getCollectors";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                tasks = parseCollecteurs(new String(req.getResponseData()));
                System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return tasks;

    }

    public ArrayList<Contrat> affichageContrats(int id_user) {
        ArrayList<Contrat> result = new ArrayList<>();
        String url = Statics.BASE_URL + "api/allcontratsjson/" + id_user;
        req.setUrl(url);
        req.setPost(false);
        req.setHttpMethod("GET");
        System.out.println(req);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                contrattasks = parseContrats(new String(req.getResponseData()));
                System.out.println("Task : " + tasks);
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return contrattasks;
    }

    //Delete 
    public boolean deleteContrat(int id) {
        String url = Statics.BASE_URL + "api/deleteContratJSON/" + id;
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

    //Update 
    public boolean modifierContrat(Contrat contrat) {
        String url = Statics.BASE_URL + "api/updateContratJSON/" + contrat.getId() + "?Description=" + contrat.getDescription() + "&DateDebut=" + contrat.getDate_debut() + "&DateFin=" + contrat.getDate_fin() + "&TypeContrat=" + contrat.getType_contrat() + "&Montant=" + contrat.getMontant();
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        return resultOk;
    }

    public void sendsms(String str,String collecteur, String contrat) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new PhoneNumber("+216" + str), // To number
                new PhoneNumber("+15675871771"), // From number
                "Bonjour "+collecteur+", vous êtes invités pour montrer le contrat n°: "+contrat
        ).create();
        System.out.println(message.getSid());

    }

   /* public void sendmail(String to, int invitation_id, int id_contrat, int id_collecteur) {

        String from = "eco.collect.esprit@gmail.com";
        final String username = "eco.collect.esprit@gmail.com";
        final String password = "elcwxampunhdqlku";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            javax.mail.Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(javax.mail.Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Invitation Request");
            message.setContent(
                    "this is email contrat invitation\n"
                    + "<br />\n"
                    + "<a href=\"http://127.0.0.1:8000/contrat/ContratInvitationRequest/" + invitation_id + "/" + id_contrat + "/" + id_collecteur + "/accepter\">Accepter</a>\n"
                    + "&nbsp;\n"
                    + "<a href=\"http://127.0.0.1:8000/contrat/ContratInvitationRequest/" + invitation_id + "/" + id_contrat + "/" + id_collecteur + "/refuser\">Refuser</a>",
                    "text/html");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }*/

}
