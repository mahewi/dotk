package com.example.ohjelmoinninalkeet;

import com.example.ohjelmoinninalkeet.AloitusView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.Sizeable;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.navigator.Navigator;

/** A start view for navigating to the main view */
public class PythonUI extends HorizontalSplitPanel implements View {
		
	public static final String NAME = "pythonView";
	private Button takaisin = new Button("Takaisin");
	private Label otsikko = new Label("<h1 class='python'>Python</h1>");
	private Button teht1 = new Button("Muuttujat");
	private Button teht2 = new Button("Ehtolauseet");
	private Button teht3 = new Button("Toistorakenteet");
	private Button teht4 = new Button("Metodit");
	private Panel ohjePaneeli = new Panel();
	private Label ohje = new Label("");
	Tehtava t1 = new Tehtava();
	
    public PythonUI() {
    	initLayout();
    }
    
    public void initLayout() {
		
    	
    	String ohjeTeksti = "<p><b class='esittelyotsikko'>Teht‰v‰n‰kym‰</b> <br></br> Teht‰v‰n‰kym‰ss‰ voit valita haluamasi teht‰v‰tyypin."
    			+ " Painamalla tietty‰ teht‰v‰tyyppi‰ ohjelma avaa automaattisesti uuden teht‰v‰n.</p>";
    	ohje.setContentMode(ContentMode.HTML);
    	
    	// Button, josta p‰‰see takaisin aloitusn‰kym‰‰n.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(AloitusView.NAME);
		    }
		});
		
		teht1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Muuttujat");
			}
		});
		
		teht2.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Ehtolauseet");
			}
		});
		
		teht3.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Toistorakenteet");
			}
		});
		
		teht4.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Metodit");
			}
		});
		
		setLocked(true);
		setSplitPosition(15, HorizontalSplitPanel.Unit.PERCENTAGE);
		
		
		VerticalLayout vasenLay = new VerticalLayout();
		VerticalLayout oikeaLay = new VerticalLayout();
		addComponent(vasenLay);
		addComponent(oikeaLay);
		
        ohje.setValue(ohjeTeksti);
		ohjePaneeli.setWidth("80%");
		ohjePaneeli.setHeight("60%");
		ohjePaneeli.setContent(ohje);
        
        otsikko.setContentMode(ContentMode.HTML);
        teht1.setStyleName("tehtavaStyle");
        teht2.setStyleName("tehtavaStyle");
        teht3.setStyleName("tehtavaStyle");
        teht4.setStyleName("tehtavaStyle");
        
        vasenLay.addComponent(takaisin);
        vasenLay.addComponent(otsikko);
        vasenLay.addComponent(teht1);
        vasenLay.addComponent(teht2);
        vasenLay.addComponent(teht3);
        vasenLay.addComponent(teht4);
        oikeaLay.addComponent(ohjePaneeli);
        
        
        vasenLay.setComponentAlignment(teht1, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht2, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht3, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht4, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(takaisin, Alignment.MIDDLE_CENTER);
        oikeaLay.setComponentAlignment(ohjePaneeli, Alignment.MIDDLE_CENTER);
        
    }
        
    @Override
    public void enter(ViewChangeEvent event) {
    	// TODO Auto-generated method stub
    }
}
