/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
package Event.gui;

import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Event;
import com.mycompany.myapp.gui.BaseForm;
import com.mycompany.myapp.services.ServiceEvent;
import java.util.ArrayList;

/**
 *
 * @author Hamza
 */
/*public class ShowEvent  extends BaseForm {

    
        private ServiceEvent serviceEvent;
    
    public ShowEvent(Resources res) {
        serviceEvent = ServiceEvent.getInstance();
        ArrayList<Event> ee = new  ArrayList<Event>() ;
                ee = getAllEvents() ;
                
                for(Event event : ee) {
            Container container = new Container(BoxLayout.y());
    
    Label titleLabel = new Label("Title: " + event.getTitle());
    Label descriptionLabel = new Label("Description: " + event.getDescription());
    Label startDateLabel = new Label("Start Date: " + event.getDatedebut());
    Label endDateLabel = new Label("End Date: " + event.getDatefin());
    
    container.add(titleLabel).add(descriptionLabel).add(startDateLabel).add(endDateLabel);
    
    add(container);
    }    
        
    }
 
        
        public void showEvent(Event event) {
    Container container = new Container(BoxLayout.y());
    
    Label titleLabel = new Label("Title: " + event.getTitle());
    Label descriptionLabel = new Label("Description: " + event.getDescription());
    Label startDateLabel = new Label("Start Date: " + event.getDatedebut());
    Label endDateLabel = new Label("End Date: " + event.getDatefin());
    
    container.add(titleLabel).add(descriptionLabel).add(startDateLabel).add(endDateLabel);
    
    add(container);
}
    
    

        public void createEvent(String title, String description, String startDate, String endDate) {
        Event event = new Event(title, description, startDate, endDate);
        serviceEvent.ajoutEvent(event);
    }
    
    public void updateEvent(int eventId, String title, String description, String startDate, String endDate) {
        Event event = new Event(eventId, title, description, startDate, endDate);
        serviceEvent.modifierEvent(event);
    }
    
    public ArrayList<Event> getAllEvents() {
        return serviceEvent.getAllEvents();
    }

    
}
*/