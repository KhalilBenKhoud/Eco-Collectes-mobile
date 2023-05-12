/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonce.gui;

import com.mycompany.myapp.services.ServiceAnnonce;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Annonce;
import com.twilio.twiml.voice.Say;

/**
 *
 * @author GAMERS
 */
public class showOne extends Form {

    public Container home;

    public showOne(int id) {
        super("showOne");
        ServiceAnnonce sa = new ServiceAnnonce();
        Annonce a = sa.getOneById(id);
        System.out.println(a.toString());
        
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Label titreLabel = new Label("Titre:");
        Label titreField = new Label(a.getTitre());

        Label descLabel = new Label("Description:");
        Label descField = new Label(a.getDescAnnonce());

        Label dateCreateLabel = new Label("Date de Creation:");
        Label dateCreateField = new Label(a.getDateCreation().toString());

        Label dateModifLabel = new Label("Date de Modification:");
        Label dateModifField = new Label(a.getDateCreation().toString());


        add(titreLabel).add(titreField).add(descLabel).add(descField).add(dateCreateLabel).add(dateCreateField).add(dateModifLabel).add(dateModifField);

        Toolbar tb = this.getToolbar();

        //  System.setProperty("com.codename1.io.disableSSLVerification", "true");
        tb.addMaterialCommandToSideMenu("Catalogue", FontImage.MATERIAL_GAMEPAD, (ActionListener) (ActionEvent evt) -> {
            Ajout mysportGui = new Ajout();
            mysportGui.show();
        });

        /* tb.addMaterialCommandToSideMenu("Commande", FontImage.MATERIAL_PEOPLE, (ActionListener) (ActionEvent evt) -> {
            CoachingGui coachingGui = new CoachingGui();
            coachingGui.show();
       });*/
        tb.addMaterialCommandToSideMenu("Ajouter Une Annonce", FontImage.MATERIAL_FAVORITE, (ActionListener) (ActionEvent evt) -> {
            Ajout favorisGui = new Ajout();
            favorisGui.show();
        });
    }
}
