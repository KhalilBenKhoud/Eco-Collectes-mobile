package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.utils.Statics;

import esprit.pidev.entitys.Forum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unused")
public class ServiceForum {
		   
	public static ServiceForum instance=null;
    public boolean resultOK;
    private ConnectionRequest req;
    public ArrayList<Forum> forum;
    private Forum loggedUser = new Forum();
    public ArrayList<Forum> forum1;
   
    public ServiceForum() {
         req = new ConnectionRequest();
    }

    public static ServiceForum getInstance() {
        if (instance == null) {
            instance = new ServiceForum();
        }
        return instance;
    }

    public ArrayList<Forum> parseUser(String jsonText){
        ArrayList<Forum> forum = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String,Object> ClientListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String,Object>> list = (List<Map<String,Object>>)ClientListJson.get("root");

            //Parcourir la liste des tâches Json
            for(Map<String,Object> obj : list){
                //Création des tâches et récupération de leurs données
                Forum a = new Forum();
                System.out.println("-------------");
                System.out.println(obj.get("username"));
                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int)id);
                a.setTitre(obj.get("titre").toString());
                a.setContenu(obj.get("contenu").toString());
                a.setPhoto(obj.get("photo").toString());
                a.setCategorie(obj.get("categorie").toString());

                //Ajouter la tâche extraite de la réponse Json à la liste
                forum.add(a);
            }

        } catch (Exception ex) {
             ex.printStackTrace();
        }

        return forum;
    }

	       
	public boolean addForum(Forum a) {
        String url = Statics.BASE_URL + "newArticleJSON/new?" 
            + "titre=" + a.getTitre()
            + "&contenu=" + a.getContenu()
            + "&photo=" + a.getPhoto();
       //     + "&categorie=" + a.getCategorie();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

	         
	public boolean modifier(Forum a) {
		String url = Statics.BASE_URL + "/updateArticleJSON/" + a.getId() + "?titre=" + a.getTitre() + "&contenu=" + a.getContenu() + "&photo=" + a.getPhoto() + "&categorie=" + a.getCategorie();
		req.setUrl(url);
	   
		req.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
			   resultOK = req.getResponseCode() == 200; // Code response Http 200 ok
				req.removeResponseListener(this);
			}
		});
	   
		NetworkManager.getInstance().addToQueueAndWait(req);
		return resultOK;
	}
	
	public boolean deleteForum(int forumId) {
		String url = Statics.BASE_URL + "/deleteArticleJSON/" + forumId;
		req.setUrl(url);
	
		req.addResponseListener(new ActionListener<NetworkEvent>() {
			@Override
			public void actionPerformed(NetworkEvent evt) {
				resultOK = req.getResponseCode() == 200;
				req.removeResponseListener(this);
			}
		});
	
		NetworkManager.getInstance().addToQueueAndWait(req);
		return resultOK;
	}
	   




	             
	       public ArrayList<Forum> Allarticles(){
	         String url = Statics.BASE_URL + "/showallarticle";
	        req.setUrl(url);
	        req.setPost(false);
	        req.addResponseListener(new ActionListener<NetworkEvent>() {
	            @Override
	            public void actionPerformed(NetworkEvent evt) {
	                forum = parseUser(new String(req.getResponseData()));
	                req.removeResponseListener(this);
	            }
	        });
	        NetworkManager.getInstance().addToQueueAndWait(req);
	        return forum;

	       }
	       
}

