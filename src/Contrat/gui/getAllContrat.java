/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */
package Contrat.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.RoundBorder;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.entities.Contrat;
import com.mycompany.myapp.services.ServiceContrat;

/**
 * The g form
 *
 * @author amine
 */
public class getAllContrat extends BaseForm {

    public getAllContrat(Resources res) {

        super("getAllContrat", BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Tout les contrats");
        getContentPane().setScrollVisible(false);

        super.addSideMenu(res);
        tb.addSearchCommand(e -> {
        });

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
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, spacer1, spacer2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Ajouter", barGroup);
        all.setUIID("SelectBar");
        all.setPreferredW(999);
        all.addActionListener((evt) -> {
            AddContratForm aef = new AddContratForm(res);
            aef.show();
        });
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, all),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        for (Contrat e : ServiceContrat.getInstance().affichageContrats(11)) {
            addButton(res, e);
        }

    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void addTab(Tabs swipe, Image img, Label spacer, String likesStr, String commentsStr, String text) {
        int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        if (img.getHeight() < size) {
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
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container page1
                = LayeredLayout.encloseIn(
                        image,
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

    private void addButton(Resources res, Contrat e) {
        int height = Display.getInstance().convertToPixels(11.5f);
        int width = Display.getInstance().convertToPixels(14f);
        Button image = new Button();
        image.setUIID("Label");
        Container cnt = BorderLayout.west(image);
        //cnt.setLeadComponent(image);
        Label spacer1 = new Label("=================================================================");

        TextArea tDescription = new TextArea("Description: " + e.getDescription());
        tDescription.setUIID("NewsTopLine");
        tDescription.setEditable(false);
        TextArea tDate_debut = new TextArea("Date debut: " + e.getDate_debut());
        tDate_debut.setUIID("NewsTopLine");
        tDate_debut.setEditable(false);
        TextArea tDate_fin = new TextArea("Date fin: " + e.getDate_fin());
        tDate_fin.setUIID("NewsTopLine");
        tDate_fin.setEditable(false);
        TextArea tStatut_contrat = new TextArea("Statut du contrat: " + e.getStatut_contrat());
        tStatut_contrat.setUIID("NewsTopLine");
        tStatut_contrat.setEditable(false);
        TextArea tType_contrat = new TextArea("Type du contrat: " + e.getType_contrat());
        tType_contrat.setUIID("NewsTopLine");
        tType_contrat.setEditable(false);
        TextArea tMontant = new TextArea("Montant: " + e.getMontant());
        tMontant.setUIID("NewsTopLine");
        tMontant.setEditable(false);

        Container C1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        Button btnEdit = new Button("Modifier");
        Button btnShow = new Button("Show");
        Button btnDelete = new Button("Supprimer");
        btnDelete.addActionListener((evt) -> {
            ServiceContrat.getInstance().deleteContrat(e.getId());
            Dialog.show("Remove Contrat", "Successfully deleted", new Command("OK"));
            getAllContrat gae = new getAllContrat(res);
            gae.show();
        });
        btnShow.addActionListener((evt) -> {
            ShowContrat eef = new ShowContrat(res, e);
            eef.show();
        });
        btnEdit.addActionListener((evt) -> {
            EditContratForm eef = new EditContratForm(res, e);
            eef.show();
        });

        //,BoxLayout.encloseX(tDateF,tStatus),BoxLayout.encloseX(tNbr,tLieu),BoxLayout.encloseX(tClient,tType)
        add(cnt);
        cnt.add(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE,
                BoxLayout.encloseY(
                        tDescription, tDate_debut, tDate_fin, tStatut_contrat, tType_contrat, tMontant, BoxLayout.encloseX(btnShow,btnEdit, btnDelete), spacer1
                ));

        // image.addActionListener(e -> ToastBar.showMessage(nom_event, FontImage.MATERIAL_INFO));
    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }

}
