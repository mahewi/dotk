package com.example.ohjelmoinninalkeet;

import java.io.File;

import com.example.ohjelmoinninalkeet.AloitusView;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.Reindeer;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
/**
 * PythonUI-n�kym� m��rittelee Python-ohjelmointikielen "kotisivun" ohjelmassa. N�kym� sis�lt��
 * Mahdollisuuden valita eri teht�v�tyyppej�.
 * 
 * @author Marco Willgren & Tatu Sepp�-Lassila
 */
public class PythonUI extends Panel implements View {
	
	// Muuttuja jota Navigator-olio k�ytt�� n�kym�n identifioimiseen
	public static final String NAME = "pythonView";
	
	private Label otsikko = new Label("<h1 class='python'>Python</h1>");
	private Label ohje = new Label("");
	private Button takaisin = new Button("Takaisin");
	private Button teht1 = new Button("Teht�v�: Muuttujat");
	private Button teht2 = new Button("Teht�v�: Ehtolauseet");
	private Button teht3 = new Button("Teht�v�: Toistorakenteet");
	private Button teht4 = new Button("Teht�v�: Metodit");
	private Button muuttujaTuto = new Button("Tutoriaali: Muuttujat");
	private Button ehtoTuto = new Button("Tutoriaali: Ehtolauseet");
	private Button toistoTuto = new Button("Tutoriaali: Toistorakenteet");
	private Button metodiTuto = new Button("Tutoriaali: Metodit");
	private Panel ohjePaneeli = new Panel();
	private String tiedPolku;
	private Image py;
	
    public PythonUI() {
    	initLayout();
    }
    
    /**
     * Metodissa m��ritell��n n�kym�n graafinen ulkoasu ja rekister�id��n komponenttien toiminnallisuudet.
     */
    public void initLayout() {
    	String ohjeTeksti = "<p><b class='esittelyotsikko'>Teht�v�n�kym�</b> <br></br> � Teht�v�n�kym�ss� voit valita haluamasi teht�v�tyypin."
    			+ " Painamalla tietty� teht�v�tyyppi� ohjelma avaa automaattisesti uuden teht�v�n.<br></br> � Voit my�s lukea teht�v�alueisiin"
    			+ " liittyvi� tutoriaaleja (oppimismateriaaleja), jotka avustavat teht�vien tekemisess� ja alkuun p��semisess�.<br></br> � Ohjelmassa on mahdollista"
    			+ " lukea tutoriaalia ja tehd� teht�v�� samaa aikaa. Avaa vain tutoriaali ennen kuin painat teht�v��n siirtymispainiketta!<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
    			+ " � Voit muuttaa tutoriaali-ikkunan kokoa haluamasi kokoiseksi vet�m�ll� ikunnan oikeassa alalaidassa olevasta s��t�vivusta.</p>";
    	ohje.setContentMode(ContentMode.HTML);
    	
    	// Haetaan kuva /WEB-INF/images kansiosta
	    tiedPolku = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	    FileResource resource = new FileResource(new File(tiedPolku + "/WEB-INF/images/python.png"));
	    py = new Image("", resource);
	    py.setHeight("60%");
	    py.setWidth("60%");
	    
	    otsikko.setStyleName("pyOtsikkoStyle");
	    py.setStyleName("pyKuvaStyle");
	    
    	// Painike, josta p��see takaisin aloitusn�kym��n.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(AloitusView.NAME);
		    }
		});
		
		// Painike, josta p��see teht�v�n�kym��n (Editori-n�kym�).
		teht1.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Muuttujat");
			}
		});
		
		// Painike, josta p��see teht�v�n�kym��n (Editori-n�kym�).
		teht2.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Ehtolauseet");
			}
		});
		
		// Painike, josta p��see teht�v�n�kym��n (Editori-n�kym�).
		teht3.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Toistorakenteet");
			}
		});
		
		// Painike, josta p��see teht�v�n�kym��n (Editori-n�kym�).
		teht4.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				getUI().getNavigator().navigateTo(Editori.NAME + "/" + "Metodit");
			}
		});
		
		// Painike, josta p��see tutoriaaliin
		muuttujaTuto.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				MuuttujatPopup muuttujat = new MuuttujatPopup();
				UI.getCurrent().addWindow(muuttujat);
			}
		});
		
		// Painike, josta p��see tutoriaaliin
		ehtoTuto.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				EhtolauseetPopup ehto = new EhtolauseetPopup();
				UI.getCurrent().addWindow(ehto);
			}
		});
		
		// Painike, josta p��see tutoriaaliin
		toistoTuto.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				ToistorakenteetPopup toisto = new ToistorakenteetPopup();
				UI.getCurrent().addWindow(toisto);
			}
		});
		
		// Painike, josta p��see tutoriaaliin
		metodiTuto.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				MetoditPopup metodit = new MetoditPopup();
				UI.getCurrent().addWindow(metodit);
			}
		});
		
		HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
		hsplit.setLocked(true);
		hsplit.setSplitPosition(20, HorizontalSplitPanel.Unit.PERCENTAGE);
		hsplit.setStyleName(Reindeer.SPLITPANEL_SMALL);
		setContent(hsplit);
		
		VerticalLayout vasenLay = new VerticalLayout();
		VerticalLayout oikeaLay = new VerticalLayout();
		HorizontalLayout hlay = new HorizontalLayout();

		hsplit.addComponent(vasenLay);
		hsplit.addComponent(oikeaLay);
		
		hlay.setSizeFull();
		hlay.setStyleName("pyHlayStyle");
		hlay.addComponent(otsikko);
		hlay.addComponent(py);
		hlay.setComponentAlignment(otsikko, Alignment.BOTTOM_CENTER);
		hlay.setComponentAlignment(py, Alignment.MIDDLE_CENTER);
		
        ohje.setValue(ohjeTeksti);
		ohjePaneeli.setWidth("80%");
		ohjePaneeli.setHeight("60%");
		ohjePaneeli.setContent(ohje);
        
        otsikko.setContentMode(ContentMode.HTML);
        teht1.setStyleName("tehtavaStyle");
        teht2.setStyleName("tehtavaStyle");
        teht3.setStyleName("tehtavaStyle");
        teht4.setStyleName("tehtavaStyle");
        muuttujaTuto.setStyleName("tehtavaStyle");
        ehtoTuto.setStyleName("tehtavaStyle");
        toistoTuto.setStyleName("tehtavaStyle");
        metodiTuto.setStyleName("tutoriaaliStyle");
        takaisin.setStyleName(Runo.BUTTON_DEFAULT);
        
        // Lis�t��n komponentit layoutteihin
        vasenLay.addComponent(takaisin);
        vasenLay.addComponent(hlay);
        vasenLay.addComponent(muuttujaTuto);
        vasenLay.addComponent(ehtoTuto);
        vasenLay.addComponent(toistoTuto);
        vasenLay.addComponent(metodiTuto);
        vasenLay.addComponent(teht1);
        vasenLay.addComponent(teht2);
        vasenLay.addComponent(teht3);
        vasenLay.addComponent(teht4);
        oikeaLay.addComponent(ohjePaneeli);
        
        // M��ritet��n komponenttien sijainnit layouteissa
        vasenLay.setComponentAlignment(teht1, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht2, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht3, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(teht4, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(muuttujaTuto, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(ehtoTuto, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(toistoTuto, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(metodiTuto, Alignment.MIDDLE_CENTER);
        vasenLay.setComponentAlignment(takaisin, Alignment.MIDDLE_CENTER);
        oikeaLay.setComponentAlignment(ohjePaneeli, Alignment.MIDDLE_CENTER);
        
    }
        
    @Override
    public void enter(ViewChangeEvent event) {
    	// TODO Auto-generated method stub
    }
}
