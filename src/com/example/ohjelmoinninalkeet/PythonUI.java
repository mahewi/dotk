package com.example.ohjelmoinninalkeet;

import com.example.ohjelmoinninalkeet.AloitusView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Link;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.navigator.Navigator;

/** A start view for navigating to the main view */
public class PythonUI extends VerticalLayout implements View {
		
	public static final String NAME = "pythonView";
	private Button takaisin = new Button("Takaisin");
	
    public PythonUI() {
    	/**
    	Link lnk = new Link("Message: Testi", new ExternalResource("#!" + AloitusView.NAME));
        addComponent(lnk);
        **/
    	
    	// Button, josta p‰‰see takaisin aloitusn‰kym‰‰n.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(AloitusView.NAME);
		    }
		});
        addComponent(takaisin);
    }        
        
    @Override
    public void enter(ViewChangeEvent event) {
    	// TODO Auto-generated method stub
    }
}
