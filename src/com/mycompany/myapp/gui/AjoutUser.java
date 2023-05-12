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
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

public class AjoutUser  extends BaseForm {
       public AjoutUser (Resources res) {
        
          super(BoxLayout.y());
//        Toolbar tb = new Toolbar(true);
//        setToolbar(tb);
//      tb.setTitle("Liste des users");
//     getContentPane().setScrollVisible(true);
        super.addSideMenu(res);

      
        setTitle("Ajouter un user");
        setLayout(BoxLayout.y());
        
        TextField tfNom = new TextField("","username");
         tfNom.setUIID("TextFieldBlack"); 
        
          TextField tfemail = new TextField("","email user");
         tfemail.setUIID("TextFieldBlack"); 
         
       TextField tftelephone = new TextField("","phone ");
         tftelephone.setUIID("TextFieldBlack"); 
         TextField tfgender = new TextField("","gender ");
         tfgender.setUIID("TextFieldBlack"); 

         TextField tfblock = new TextField("","address ");
         tfblock.setUIID("TextFieldBlack"); 
         

       
        TextField tfmdp = new TextField("","password");
                 tfmdp.setUIID("TextFieldBlack"); 
        
       
      

        Button btnValider = new Button("Ajouter Client");
        
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((tfNom.getText().length()==0)||(tfemail.getText().length()==0)||(tfmdp.getText().length()==0)||(tftelephone.getText().length()==0)||(tfgender.getText().length()==0)||(tfblock.getText().length()==0) )
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                
             else
                {
                    try {
                      
                        User a;
                        a = new User(Integer.parseInt(tftelephone.getText()),tfemail.getText(), tfNom.getText() ,tfblock.getText() , tfmdp.getText() ,tfgender.getText());
                        
                        if( UserService.getInstance().addUser(a)){
                            Dialog.show("Success","Connection accepted",new Command("OK"));
                         ToastBar.Status status = ToastBar.getInstance().createStatus();
                    status.setShowProgressIndicator(true);
   //status.setIcon(res.getImage("done.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));
                              status.setMessage("Client ajouté avec succes");
                                                  status.setExpires(10000);   
                      status.show();
                      new listUserForm(res).show();
                        }else
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        
                    } catch (NumberFormatException e) {
                        Dialog.show("ERROR", "Status must be a number", new Command("OK"));
                    }
                    
                }
                
                
            }
        });    
        
        
        addAll(tfNom , tfemail,tfmdp,tftelephone, tfgender,tfblock, btnValider);
   
     }
}
