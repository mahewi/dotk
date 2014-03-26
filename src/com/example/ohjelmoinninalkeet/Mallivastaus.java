package com.example.ohjelmoinninalkeet;

import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

/**
 * Luokka m‰‰rittelee ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Mallivastaus"-painiketta.
 * Ikkuna sis‰lt‰‰ teht‰v‰n mallivastauksen niin kirjoitetussa muodossa kuin graafisena esityksen‰. 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 *
 */
public class Mallivastaus extends Window {
	
	private TextArea vastaus = new TextArea();
	
	public Mallivastaus(String mallivastaus) {
    
		super("Teht‰v‰n mallivastaus");
	    center();
	    
	    setHeight("80%");
	    setWidth("80%");
		
		vastaus.setWidth(80, TextArea.Unit.PERCENTAGE);
		vastaus.setRows(30);
	    
	    Button sulje = new Button("Sulje");
	    sulje.setStyleName(Runo.BUTTON_DEFAULT);
	    sulje.setWidth("25%");
	    
	    vastaus.setValue(mallivastaus);
	    
	    HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
		hsplit.setLocked(true);
		hsplit.setSplitPosition(50, HorizontalSplitPanel.Unit.PERCENTAGE);
		hsplit.setStyleName(Reindeer.SPLITPANEL_SMALL);
		hsplit.setSizeFull();
		
		VerticalLayout vasenLay = new VerticalLayout();
		VerticalLayout oikeaLay = new VerticalLayout();
		
		vasenLay.addComponent(vastaus);
		vasenLay.setComponentAlignment(vastaus, Alignment.MIDDLE_CENTER);
		
		hsplit.addComponent(vasenLay);
		hsplit.addComponent(oikeaLay);
		
		setContent(hsplit);
	    
	    sulje.addClickListener(new ClickListener() {
	        public void buttonClick(ClickEvent event) {
	            close();
	        }
	    });
    
	}
}
