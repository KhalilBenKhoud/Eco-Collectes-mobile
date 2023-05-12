/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Annonce.gui;

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
import com.mycompany.myapp.services.ServiceAnnonce;

/**
 *
 * @author GAMERS
 */
public class Ajout extends Form {

    public Container home;
    ServiceAnnonce sa = new ServiceAnnonce();

    public TextField titreField;
    public TextField descField;
    
    public Ajout() {
        super("Ajout");
        setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        Label titreLabel = new Label("Titre:");
        titreField = new TextField();

        Label descLabel = new Label("Description:");
        descField = new TextField();

        Button sauvegarderButton = new Button("Sauvegarder");
        sauvegarderButton.addActionListener(evt -> sauvegarder());

        add(titreLabel).add(titreField).add(descLabel).add(descField).add(sauvegarderButton);
        
        
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

    private void sauvegarder() {
        String titre = titreField.getText();
        String desc = descField.getText();
        System.out.println(titre + ", " + desc);
        
        // Save the titre to the database, or perform any other required action here
        sa.addAnnonce(titre, desc);
    }
}
