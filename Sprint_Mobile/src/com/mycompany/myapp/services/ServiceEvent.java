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
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.entities.User;
import static com.mycompany.myapp.services.ServiceContrat.resultOk;

import com.mycompany.myapp.utils.Statics;
import com.twilio.Twilio;
import static com.twilio.example.Example.ACCOUNT_SID;
import static com.twilio.example.Example.AUTH_TOKEN;
import static com.twilio.jwt.taskrouter.UrlUtils.tasks;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hamza
 */
public class ServiceEvent {
    //singleton 
    public static ServiceEvent instance = null;

    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    public ArrayList<Event> eventtasks;
    public LinkedHashMap<String, Object> event = new LinkedHashMap<>();
   

    public static ServiceEvent getInstance() {
        if (instance == null) {
            instance = new ServiceEvent();
        }
        return instance;
    }

    public ServiceEvent() {
        req = new ConnectionRequest();
    }
  //ajout 
    //http://127.0.0.1:8000/newevent?title=sweet&description=gggc&datedebut=2024-04-18&datefin=2025-05-18
    public void ajoutEvent(Event event) {

        String url = Statics.BASE_URL + "newevent"+"?title=" + event.getTitle() +"&description="+ event.getDescription() +"&datedebut=" + event.getDatedebut() + "&datefin=" + event.getDatefin();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());
            System.out.println("data == " + str);
        });

        NetworkManager.getInstance().addToQueueAndWait(req);

    }
    //update
      public boolean modifierEvent(Event event) {
        String url = Statics.BASE_URL + "updateEvent/" + event.getId() + "?Title=" + event.getTitle()+ "&Description=" + event.getDescription() + "&datedebut=" + event.getDatedebut() + "&datefin=" + event.getDatefin();
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

    
    //affichage
    
    public ArrayList<Event>affichageEvents() {
        ArrayList<Event> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"allevents";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapEvents = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapEvents.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Event re = new Event();
                        
                        //dima id fi codename one float 5outhouha
                        
                        String objet = obj.get("title").toString();
                        
                        String description = obj.get("description").toString();
                        String datedebut = obj.get("datedebut").toString();
                        String datefin = obj.get("datefin").toString();

                        re.setTitle(objet);
                        re.setDescription(description);
                        re.setDatedebut(datedebut);
                        re.setDatefin(datefin);

                        //insert data into ArrayList result
                        result.add(re);
                       
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }

    
    //affichage
      
     /* public ArrayList<Event> getAllEvents() {
    ArrayList<Event> events = new ArrayList<>();

    String url = Statics.BASE_URL + "allevents";
    req.setUrl(url);

    req.addResponseListener((NetworkEvent evt) -> {
        try {
            JSONParser parser = new JSONParser();
            Map<String, Object> response = parser.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

            // Assuming the response contains a JSON array of events
            List<Map<String, Object>> eventsJson = (List<Map<String, Object>>) response.get("events");

            for (Map<String, Object> eventJson : eventsJson) {
                int id = Integer.parseInt(eventJson.get("id").toString());
                String title = eventJson.get("title").toString();

                String description = eventJson.get("description").toString();
                String startDate = eventJson.get("datedebut").toString();
                String endDate = eventJson.get("datefin").toString();

                Event event = new Event(id, title, description, startDate, endDate);
                events.add(event);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    });

    NetworkManager.getInstance().addToQueueAndWait(req);

    return events;
}
*/
      
      
   
    /*public ArrayList<Event> parseEvents(String jsonText) {
        try {
            eventtasks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> tasksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasksListJson.get("root");

            for (Map<String, Object> obj : list) {
                Event e = new Event();

                float id = Float.parseFloat(obj.get("id").toString());
                String title = obj.get("title").toString();

                String description = obj.get("description").toString();
                String datedebut = obj.get("datedebut").toString().substring(0, obj.get("datedebut").toString().indexOf('T'));
                String datefin = obj.get("datefin").toString().substring(0, obj.get("datefin").toString().indexOf('T'));
                
                //////////////event////////////////
                event = (LinkedHashMap) obj.get("collecteur");
                System.out.println(event);
                Event u = new Event();
                if (event != null) {
                    for (Map.Entry<String, Object> entry : event.entrySet()) {
                        if (entry.getKey().contains("title")) {
                            u.setTitle(entry.getValue().toString());
                        }
                        if (entry.getKey().contains("description")) {
                            u.setDescription(entry.getValue().toString());
                        }
                      if (entry.getKey().contains("datedebut")) {
                            u.setDatedebut(entry.getValue().toString());
                        }
                     
                        if (entry.getKey().contains("datefin")) {
                            u.setDatefin(entry.getValue().toString());
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
                e.setTitle(title);
                e.setDescription(description);
                e.setDatedebut(datedebut);
                e.setDatefin(datefin);
                

                eventtasks.add(e);
            }
        } catch (IOException ex) {

        }
        return eventtasks;
    }*/

}
