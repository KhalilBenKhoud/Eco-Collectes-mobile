/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Event.gui;

/**
 *
 * @author Hamza
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Contrat.gui.getAllContrat;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceContrat;
import com.mycompany.myapp.services.ServiceEvent;

import java.text.SimpleDateFormat;

/**
 *
 * @author hamza
 */

public class AddEventForm extends BaseForm{
    
    
    Form current;
    public AddEventForm(Resources res ) {
         super("Add Contrat Form", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Ajouter un evennement");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        tb.addSearchCommand(e -> {});
        
        Tabs swipe = new Tabs();

        Label spacer1 = new Label();
        Label spacer2 = new Label();
        addTab(swipe, res.getImage("news-item.jpg"), spacer1, "", "", "");
        addTab(swipe, res.getImage("dog.jpg"), spacer2, "", "", "");
                
        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();
        
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for(int iter = 0 ; iter < rbs.length ; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }
                
        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if(!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });
        
        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));
        
        ButtonGroup barGroup = new ButtonGroup();
      
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
    
        
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        
    //http://127.0.0.1:8000/newevent?title=sweet&description=gggc&datedebut=2024-04-18&datefin=2025-05-18
       
    
     TextField Title1 = new TextField("", "entrer titre!!");
      TextField Title2 = new TextField("", "entrer titre!!");
       TextField Title3 = new TextField("", "entrer titre!!");
    
    TextField Title = new TextField("", "entrer titre!!");
        Title.setUIID("TextFieldBlack");
        addStringValue("title",Title);

        TextField Description = new TextField("", "entrer description!!");
        Description.setUIID("TextFieldBlack");
        addStringValue("Description",Description);
        Picker datedebut = new Picker();
        datedebut.setUIID("TextFieldBlack");
        datedebut.setType(Display.PICKER_TYPE_DATE);
        addStringValue("Date debut",datedebut);
         Picker datefin = new Picker();
        datefin.setUIID("TextFieldBlack");
        datefin.setType(Display.PICKER_TYPE_DATE);
        addStringValue("Date fin",datefin);
       
        
        
        Button btnAjouter = new Button("Ajouter");
        addStringValue("", btnAjouter);

        
        //onclick button event 

        btnAjouter.addActionListener((e) -> {
            
            
            try {
                
                    if(Title.getText().equals("") ||Description.getText().equals("") || datedebut.getText().equals("") || datefin.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données","","Annuler", "OK");
                }
                
                else {
                    InfiniteProgress ip = new InfiniteProgress();; 
                
                    final Dialog iDialog = ip.showInfiniteBlocking();
                    
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    
                    Event event = new Event();
                    //title
                   event.setTitle(Title.getText());
             
                   event.setDescription(Description.getText());
                   
                    event.setDatedebut( "2021-12-12" );
                   event.setDatefin( "2021-12-12" );
                   
                   
                   SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   String formattedDate = dateFormat.format( datedebut.getDate() );
                   String formattedDate2 = dateFormat.format( datefin.getDate() );
                   
                   System.out.println("hehe"+ formattedDate );
                   
                    event.setDatedebut(  formattedDate ) ;
                   event.setDatefin( formattedDate2 );
                   
                   
                   //event.setDatedebut(String.valueOf( datedebut.getDate()) ) ;
                   //event.setDatefin(String.valueOf(datefin.getDate()));
                  
                   
                    
                    ServiceEvent.getInstance().ajoutEvent(event);
                   
                    iDialog.dispose();
                    Dialog.show("Add Event", "Successfully added",new Command("OK"));
                    
                    refreshTheme();
                            
                }
                
            }catch(Exception ex ) {
                ex.printStackTrace();
            }
            
            
            
            
            
        });
        
        
    }

    private void addStringValue(String s, Component v) {
        
        add(BorderLayout.west(new Label(s,"PaddedLabel"))
        .add(BorderLayout.CENTER,v));
        add(createLineSeparator(0xeeeeee));
    }

    
    
    
    public void bindButtonSelection(Button btn , Label l ) {
        
        btn.addActionListener(e-> {
        if(btn.isSelected()) {
            updateArrowPosition(btn,l);
        }
    });
    }

    private void updateArrowPosition(Button btn, Label l) {
        
        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth()  / 2  - l.getWidth() / 2 );
        l.getParent().repaint();
    }
    
     private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if(img.getHeight() < size) {
            img = img.scaledHeight(size);
        }
        Label likes = new Label(likesStr);
        Style heartStyle = new Style(likes.getUnselectedStyle());
        heartStyle.setFgColor(0xff2d55);
        FontImage heartImage = FontImage.createMaterial(FontImage.MATERIAL_FAVORITE, heartStyle);
        likes.setIcon(heartImage);
        likes.setTextPosition(RIGHT);

        Label comments = new Label(commentsStr);
        FontImage.setMaterialIcon(comments, FontImage.MATERIAL_CHAT);
        if(img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");
        
        Container page1 = 
            LayeredLayout.encloseIn(
                
                overlay,
                BorderLayout.south(
                    BoxLayout.encloseY(
                            new SpanLabel(text, "LargeWhiteText"),
                           
                            spacer
                        )
                )
            );

        swipe.addTab("", page1);
    }
    
}