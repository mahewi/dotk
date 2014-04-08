package com.example.ohjelmoinninalkeet;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import com.vaadin.event.MouseEvents;
import com.vaadin.server.ExternalResource;
import com.vaadin.server.FileResource;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Image;
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
 * Ikkuna sis‰lt‰‰ teht‰v‰n mallivastauksen niin kirjoitetussa muodossa kuin videoesityksen‰. 
 * 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 */
public class Mallivastaus extends Window {
	
	Embedded video = new Embedded(null);
	private TextArea vastaus = new TextArea();
	private Panel ohjeet = new Panel();
	private Panel kuvaPanel = new Panel();
	private Label ohje = new Label();
	private Label ilmoitus = new Label();
	private Label tehtAnto = new Label();
	private String ohjeTeksti;
	private String ilmoitusTeksti;
	private String tiedPolku;
	private Image kuva1, kuva2, kuva3;
	private ArrayList<Image> kuvat = new ArrayList<Image>();
	private Random random;
	private FileResource res1, res2, res3;
	private Button sulje;
	
	public Mallivastaus(String[] mallivastaus, String tehtava, String videoLinkki) {
    
		super("Teht‰v‰n mallivastaus");
	    center();
	    
	    setHeight("80%");
	    setWidth("80%");
	    
	    tehtAnto.setStyleName("tehtAntoMalliStyle");
	    tehtAnto.setContentMode(ContentMode.HTML);
	    tehtAnto.setValue("<p class='teht'>" + tehtava + "</p>");
	    
		vastaus.setWidth(80, TextArea.Unit.PERCENTAGE);
		vastaus.setRows(16);
		vastaus.setStyleName("malliVastausTextStyle");
		asetaVastaus(mallivastaus);
		
	    sulje = new Button("Sulje");
	    sulje.setStyleName(Runo.BUTTON_DEFAULT);
	    sulje.setWidth("25%");
	    
	    tiedPolku = VaadinService.getCurrent().getBaseDirectory().getAbsolutePath();
	    res1 = new FileResource(new File(tiedPolku + "/WEB-INF/images/cry.png"));
	    res2 = new FileResource(new File(tiedPolku + "/WEB-INF/images/python.png"));
	    res3 = new FileResource(new File(tiedPolku + "/WEB-INF/images/thumbsdown.png"));
	    kuva1 = new Image("", res1);
	    kuva2 = new Image("", res2);
	    kuva3 = new Image("", res3);
	    kuva1.setHeight("400px");
	    kuva1.setWidth("400px");
	    kuva1.setStyleName("kuvaStyle");
	    kuvat.add(kuva1);
	    kuvat.add(kuva2);
	    kuvat.add(kuva3);
	    
	    kuvaPanel.setStyleName(Runo.PANEL_LIGHT);
	    kuvaPanel.setContent(kuva1);
	    
	    HorizontalSplitPanel hsplit = new HorizontalSplitPanel();
		hsplit.setLocked(true);
		hsplit.setSplitPosition(50, HorizontalSplitPanel.Unit.PERCENTAGE);
		hsplit.setStyleName(Reindeer.SPLITPANEL_SMALL);
		hsplit.setSizeFull();
		
		VerticalLayout vasenLay = new VerticalLayout();
		final VerticalLayout oikeaLay = new VerticalLayout();
		
		vasenLay.addComponent(tehtAnto);
		vasenLay.setComponentAlignment(tehtAnto, Alignment.MIDDLE_CENTER);
		vasenLay.addComponent(vastaus);
		vasenLay.setComponentAlignment(vastaus, Alignment.MIDDLE_CENTER);
		oikeaLay.addComponent(video);
		oikeaLay.setComponentAlignment(video, Alignment.MIDDLE_CENTER);
		oikeaLay.addComponent(ohjeet);
		oikeaLay.setComponentAlignment(ohjeet, Alignment.MIDDLE_CENTER);
		oikeaLay.addComponent(ilmoitus);
		oikeaLay.setComponentAlignment(ilmoitus, Alignment.MIDDLE_CENTER);
		oikeaLay.addComponent(kuvaPanel);
		oikeaLay.setComponentAlignment(kuvaPanel, Alignment.MIDDLE_CENTER);
		
		hsplit.addComponent(vasenLay);
		hsplit.addComponent(oikeaLay);
		
		tarkistaVideo(videoLinkki); // Kutsutaan rutiinia, joka tarkistaa onko tehtavalle asetettu vastausvideota.
		
		setContent(hsplit);
	    
		// Tapahtuman k‰sittely sulje-painikkeelle. Napin painallus sulkee ikkunan.
	    sulje.addClickListener(new ClickListener() {
	        public void buttonClick(ClickEvent event) {
	            close();
	        }
	    });
	    
	    // Tapahtuman k‰sittely kuvapaneeliin. Paneelin painallus vaihtaa n‰kyviss‰ olevan kuvan.
	    kuvaPanel.addClickListener(new MouseEvents.ClickListener() {
			@Override
			public void click(com.vaadin.event.MouseEvents.ClickEvent event) {
				kuvaPanel.setContent(annaKuva());
			}
		});	
	}
	
	/**
	 * Metodi tarkistaa onko tietokannasta haetulle teht‰v‰lle asetettu vastauvideota.
	 * Video lˆytyy -> Video asetetaan nakymaan ohjetekstin kera.
	 * Videota ei asetettu -> Vastausikkunaan piirtyy ilmoitus asiasta 
	 * @param linkki
	 */
	public void tarkistaVideo(String linkki) {
		if (linkki.equals("placeholder")) {
			ilmoitus.setContentMode(ContentMode.HTML);
			ilmoitusTeksti = "<h2 class='ilmoitus'>Teht‰v‰‰n ei ole (viel‰) saatavilla vastausvideota.</h2>";
			ilmoitus.setValue(ilmoitusTeksti);
			ohjeet.setVisible(false);
		}
		else {
			kuva1.setVisible(false);
			video.setSource(new ExternalResource(linkki));
			video.setStyleName("malliVastausVideoStyle");
	        video.setMimeType("application/x-shockwave-flash");
	        video.setParameter("allowFullScreen", "true");
			video.setWidth("560px");
			video.setHeight("315px");
			
		    ohje.setContentMode(ContentMode.HTML);
		    ohjeTeksti = "<p>ï Voit avata videon <i>fullscreen</i>-tilassa painamalla videon alapuolella olevan teht‰v‰palkin oikeanpuoleisinta paniketta. <br></br> ï Mik‰li "
		    		+ "teht‰v‰palkki ei ole n‰kyviss‰, siirr‰ hiiri videon p‰‰lle niin palkki aktivoituu.</p>";
		    ohje.setValue(ohjeTeksti);
			ohjeet.setWidth("60%");
			ohjeet.setHeight("60%");
			ohjeet.setContent(ohje);
		}
	}
	
	/**
	 * Palauttaa /images/ kansiosta satunnaisen kuvan
	 * @return Satunnainen kuva kansiosta != sama kuin n‰kyill‰ oleva kuva
	 */
	public Image annaKuva() {
		random = new Random();
		Image uusiKuva = kuvat.get(random.nextInt(kuvat.size()));

		while (kuvaPanel.getContent() == uusiKuva) {
			uusiKuva = kuvat.get(random.nextInt(kuvat.size()));
		}
		
		uusiKuva.setHeight("400px");
		uusiKuva.setWidth("400px");
		uusiKuva.setStyleName("kuvaStyle");
		return uusiKuva;
	}
	
	/**
	 * Asetetaan parametrina saatu merkkijonotaulukko vastaustekstikentt‰‰n.
	 * @param mv
	 */
	public void asetaVastaus(String[] mv) {
		String apu = "";
		for (int i = 0; i < mv.length; i++) {
			if (i == mv.length - 1) {
				apu += mv[i];
			}
			else {
				apu += mv[i] + "\n";
			}
		}
		vastaus.setValue(apu);
	}
}
