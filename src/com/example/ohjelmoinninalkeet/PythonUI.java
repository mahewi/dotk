package com.example.ohjelmoinninalkeet;

import com.example.ohjelmoinninalkeet.AloitusView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.navigator.Navigator;

/** A start view for navigating to the main view */
public class PythonUI extends VerticalLayout implements View {
		
	public static final String NAME = "pythonView";
	private Button takaisin = new Button("Takaisin");
	private Label otsikko = new Label("<h1 class='python'>Python</h1>");
	private Label test = new Label("JEE");
	
    public PythonUI() {
    	initLayout();
    }
    
    public void initLayout() {
		
    	// Button, josta pääsee takaisin aloitusnäkymään.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(AloitusView.NAME);
		    }
		});
		
        otsikko.setContentMode(ContentMode.HTML);;
        addComponent(takaisin);
        addComponent(otsikko);
        
        Link lnk = new Link("Editorinäkymään--->", new ExternalResource("#!" + Editori.NAME));
        addComponent(lnk);
    }
        
    @Override
    public void enter(ViewChangeEvent event) {
    	// TODO Auto-generated method stub
    }
}
