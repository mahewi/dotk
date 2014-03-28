package com.example.ohjelmoinninalkeet;

import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.BrowserFrame;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Flash;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
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
	
	Embedded video = new Embedded(null, new ExternalResource("https://www.youtube.com/v/mjQyXmlo46U&feature=youtu.be"));
	private TextArea vastaus = new TextArea();
	private Panel ohjeet = new Panel();
	private Label ohje = new Label();
	private String ohjeTeksti;
	
	public Mallivastaus(String mallivastaus) {
    
		super("Teht‰v‰n mallivastaus");
	    center();
	    
	    setHeight("80%");
	    setWidth("80%");
		
	    ohje.setContentMode(ContentMode.HTML);
	    ohjeTeksti = "<p>ï Voit avata videon <i>fullscreen</i>-tilassa painamalla videon alapuolella olevan teht‰v‰palkin oikeanpuoleisinta paniketta. <br></br> ï Mik‰li "
	    		+ "teht‰v‰palkki ei ole n‰kyviss‰, siirr‰ hiiri videon p‰‰lle niin palkki aktivoituu.</p>";
	    ohje.setValue(ohjeTeksti);
		ohjeet.setWidth("60%");
		ohjeet.setHeight("60%");
		ohjeet.setContent(ohje);
	    
		vastaus.setWidth(80, TextArea.Unit.PERCENTAGE);
		vastaus.setRows(30);
		vastaus.setStyleName("malliVastausTextStyle");
	    
		video.setStyleName("malliVastausVideoStyle");
        video.setMimeType("application/x-shockwave-flash");
        video.setParameter("allowFullScreen", "true");
		video.setWidth("560px");
		video.setHeight("315px");
		
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
		oikeaLay.addComponent(video);
		oikeaLay.setComponentAlignment(video, Alignment.MIDDLE_CENTER);
		oikeaLay.addComponent(ohjeet);
		oikeaLay.setComponentAlignment(ohjeet, Alignment.MIDDLE_CENTER);
		
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
