/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.myapp.gui;

/**
 *
 * @author khalil
 */

import com.mycompany.myapp.entities.User;
import com.mycompany.myapp.services.UserService;
// import com.mycompany.myapp.utils.Session;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;

public class listUserForm extends BaseForm {
    
        public listUserForm(Resources res) {

        super(BoxLayout.y());
      
        getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

       
        UserService as = new UserService();
        ArrayList<User> list = as.getAllUser();
        System.out.println(list);
        {

            for (User a : list) {

                Container c3 = new Container(BoxLayout.y());

                SpanLabel cat = new SpanLabel("Nom :" + a.getUsername());
     
                SpanLabel cat3 = new SpanLabel("Email :" + a.getEmail());
                SpanLabel cat5 = new SpanLabel("Phone :" + a.getPhone());
                SpanLabel cat6 = new SpanLabel("password :" + a.getPassword());
                SpanLabel cat7 = new SpanLabel("address :" + a.getAddress() );
                SpanLabel cat8 = new SpanLabel("Gender :" + a.getGender() );
           

                c3.add(cat);

                c3.add(cat3);
                c3.add(cat5);
                c3.add(cat6);
                c3.add(cat7);
                c3.add(cat8);
       
/*              
if (c.getUsername().equals(a.getUsername())) {

                Button Delete = new Button("Delete");
                c3.add(Delete);
                 
                Delete.getAllStyles().setBgColor(0xF36B08);
                Delete.addActionListener(e -> {
                    Dialog alert = new Dialog("Attention");
                    SpanLabel message = new SpanLabel("Etes-vous sur de vouloir supprimer ce user?");
                    alert.add(message);
                    Button ok = new Button("oui");
                    Button cancel = new Button(new Command("non"));
                    //User clicks on ok to delete account
                    ok.addActionListener(new ActionListener() {

                        public void actionPerformed(ActionEvent evt) {
                            as.Delete1(a.getId());

                            alert.dispose();
                            ToastBar.Status status = ToastBar.getInstance().createStatus();
                            status.setShowProgressIndicator(true);
                            //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                            status.setMessage("client SUPPRIMEE AVEC SUCCES");
                            status.setExpires(10000);
                            status.show();

                            refreshTheme();
                            new listUserForm(res,a).show();
                        }

                    }
                    );

        
                    alert.add(cancel);
                    alert.add(ok);
                    alert.showDialog();

                });
               Button Modifier = new Button("Modifier ");
                    c3.add(Modifier);
                     Modifier.getAllStyles().setBgColor(0xF36B08);
              Modifier.addActionListener(e -> {
                 //  new UpdateUserForm(res,a).show();
                        } 

                   
                    );

        


                System.out.println("");

                

            }*/
               add(c3);
            }}
             
        }

   
}
