package com.example.ohjelmoinninalkeet;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Runo;

import java.io.*;
import java.util.ArrayList;

/**
 * Editori-luokka muodostaa n�kym�n, jossa k�ytt�j� p��see vastaamaan Python aiheisiin teht�v��n.
 * N�kym�ss� on toiminnot vastauksen suorittamiseen Python-k��nt�j�n l�pi sek� arvioiminen vertaamalla
 * sit� oikeaa tulostetta vastaan.
 *  
 * @author Marco Willgren & Tatu Sepp�-Lassila
 */
public class Editori extends Panel implements View {
	
	// Muuttuja jota Navigator-olio k�ytt�� n�kym�n identifioimiseen
	public static final String NAME = "editoriView";
	
	// Komponentit
	VerticalLayout vlay = new VerticalLayout();
	private TextArea tekstikentta = new TextArea("");
	private TextArea tulosteAlue = new TextArea();
	private Button suorita = new Button("Suorita");
	private Button arvioi = new Button("Arvioi");
	private Button takaisin = new Button("Takaisin");
	private Button ohje = new Button("OHJE");
	private Button naytaVastaus = new Button("Mallivastaus");
	private Label otsikko = new Label("");
	private Label tehtAnto = new Label("");
	private Label tulosteOtsikko = new Label("Ohjelman tulostus (Python-k��nt�j�n l�pi ajettuna)");
	private String tehtavaTyyppi = "";
	private String tehtavanAnto;
	private String malliVastaus;
	private String videoLinkki;
	private String oikeaTuloste;
	private String avainsanat;
	private String parametri;
	private Tietokanta db;
	private String[] as;
	
	public Editori() {
		initLayout();
	}
	
	// Asetetaan komponentit n�kym��n ja rekister�id��n toiminnallisuuksia.
	public void initLayout() {
		vlay.setSizeFull();
		tekstikentta.setWidth(80, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		tulosteAlue.setWidth(80, TextArea.Unit.PERCENTAGE);
		tulosteAlue.setRows(30);
		tulosteAlue.setStyleName("tulosteStyle");
		tulosteAlue.setEnabled(false);
		arvioi.setEnabled(false);
		naytaVastaus.setEnabled(false);
		otsikko.setContentMode(ContentMode.HTML);
		suorita.setStyleName("suoritaStyle");
		arvioi.setStyleName("arvioiStyle");
		naytaVastaus.setStyleName("naytaVastausStyle");
		takaisin.setStyleName(Runo.BUTTON_DEFAULT);
		ohje.setStyleName("ohjeStyle");
		tehtAnto.setStyleName("tehtAntoStyle");
		tulosteOtsikko.setStyleName("tulosteOtsikkoStyle");
		takaisin.setWidth("250px");
		ohje.setWidth("100px");
		
		// M��ritet��n VerticalLayoutin sis��n tulevat Layoutit
		HorizontalLayout ylaHlay = new HorizontalLayout();
		HorizontalLayout alempiYlaHlay = new HorizontalLayout();
		HorizontalLayout ylaOtsikkoHlay = new HorizontalLayout();
		ylaOtsikkoHlay.setWidth("100%");
		HorizontalLayout ylaKeskiHlay = new HorizontalLayout();
		ylaKeskiHlay.setWidth("100%");
		HorizontalLayout keskiHlay = new HorizontalLayout();
		keskiHlay.setWidth("100%");
		HorizontalLayout alaHlay = new HorizontalLayout();
		alaHlay.setWidth("40%");
		
		// Asetetaan komponentit m��riteltyihin layoutteihin
		ylaHlay.addComponent(takaisin);
		alempiYlaHlay.addComponent(ohje);	
		ylaOtsikkoHlay.addComponent(otsikko);
		ylaKeskiHlay.addComponent(tehtAnto);
		ylaKeskiHlay.addComponent(tulosteOtsikko);	
		keskiHlay.addComponent(tekstikentta);
		keskiHlay.addComponent(tulosteAlue);	
		alaHlay.addComponent(suorita);
		alaHlay.addComponent(arvioi);
		alaHlay.addComponent(naytaVastaus);
		
		keskiHlay.setComponentAlignment(tekstikentta, Alignment.MIDDLE_CENTER);
		keskiHlay.setComponentAlignment(tulosteAlue, Alignment.MIDDLE_CENTER);
		
		// Asetetaan eri layoutit oikeassa j�rjestyksess� VerticalLayoutin sis�lle
		vlay.addComponent(ylaHlay);
		vlay.addComponent(alempiYlaHlay);
		vlay.addComponent(ylaOtsikkoHlay);
		vlay.addComponent(ylaKeskiHlay);
		vlay.addComponent(keskiHlay);
		vlay.addComponent(alaHlay);

		// Tapahtuman k�sittely suorita-napille
		suorita.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				tulosteAlue.setValue(toPython(tekstikentta.getValue()));
				if (tarkistaKentta(tekstikentta) == true && tarkistaKentta(tulosteAlue) == true) {
					arvioi.setEnabled(true);  // Napin painalluksen j�lkeen aktivoidaan "Arvioi"-painike
				}
				else if (tarkistaKentta(tekstikentta) == false) {
					Notification.show("VIRHE!", "Et voi suorittaa ohjelmaa, jota ei ole olemassa!", Notification.Type.ERROR_MESSAGE);
				}
				else {
					Notification.show("VIRHE!", "Ohjelmasi ei mennyt k��nt�j�st� l�pi!", Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		
		// Tapahtuman k�sittely arvioi-napille. Napin painallus avaa ponnahdusikkunan
		// ,jonka sis�lt� riippu vastauksen oikeellisuudesta.
		arvioi.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (arvioiVastaus()) {
					PalautePopup palaute = new PalautePopup("Vastaus oikein!");
					UI.getCurrent().addWindow(palaute);
				}
				else {
					PalautePopup palaute = new PalautePopup("Vastaus v��rin!");
					UI.getCurrent().addWindow(palaute);
				}
				arvioi.setEnabled(false);  // Napin painamisen j�lkeen se disabloituu
				naytaVastaus.setEnabled(true);
			}
		});
		
    	// Nappi, josta p��see takaisin Python-n�kym��n. 
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tulosteAlue.setValue("");
		    	tekstikentta.setValue("");
		    	getUI().getNavigator().navigateTo(PythonUI.NAME);
		    }
		});
		
    	// Nappi, josta aukeaa ohjen�kym�
		ohje.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	OhjePopup o = new OhjePopup();
		    	UI.getCurrent().addWindow(o);
		    }
		});
		
    	// Nappi, josta aukeaa mallivastaus-n�kym�
		// Testivideolinkki: https://www.youtube.com/v/mjQyXmlo46U&feature=youtu.be
		naytaVastaus.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	Mallivastaus mv = new Mallivastaus(malliVastaus, videoLinkki);
		    	UI.getCurrent().addWindow(mv);
		    	naytaVastaus.setEnabled(false);
		    }
		});
		
		setContent(vlay); // Asetetaan lopuksi n�kym� paneelin sis�lle
	}
	
	/**
	 * Metodi vie k�ytt�j�n antaman sy�tteen Python-k��nt�j�n l�pi. 
	 * @param code
	 * @return K�ytt�j�n kirjoittaman ohjelmakoodin tuloste Python k��nt�j�n l�pi ajettuna
	 */
	public String toPython(String code) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("test.py"));
			out.write(code);
			out.close();

			Runtime r = Runtime.getRuntime();
            Process p = r.exec("python test.py");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
            p.waitFor();
            String paluu = "";
            
            br.mark(300);
            int count = 0;
            while (br.readLine() != null) {
            	if (count == 0) {
            	}
            	count++;
            }
            
 			br.reset();
            
 			for (int i = 0; i < count; i++) {
 				if (i == count - 1) {
 					paluu = paluu + br.readLine();
 				}
 				else {
 					paluu = paluu + br.readLine() + "\n";
 				}
 			}

            return paluu;
            
		}
		catch(Exception e){return e.toString();}
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		if (event.getParameters() == null) {
			Notification.show("Nyt sattui jotain j�nn��!");
		}
		else {
			otsikko.setValue("<h1 class='editoriOtsikko'>" + event.getParameters() + "</h1>");
			tehtavaTyyppi = event.getParameters().toLowerCase();
			initDB();
		}
	}
	
	/**
	 * Metodi luo tietokantaolio ja -yhteyden. Satunnainen k�ytt�j�n aiheen mukainen 
	 * teht�v� haetaan tietokannasta.
	 */
	public void initDB() {
		db = new Tietokanta();
		ArrayList<String> apu = new ArrayList<String>();
		apu = db.annaTehtava(tehtavaTyyppi);
		tehtavanAnto = "Teht�v�: " + apu.get(0);
		oikeaTuloste = apu.get(1); // Teht�v�n oikea tuloste
		malliVastaus = apu.get(2); // Mallivastaus tekstimuodossa
		videoLinkki = apu.get(3); // Linkki mallivastausvideoon
		avainsanat = apu.get(4); // Oikeasta vastauksesta t�ytyy l�yty�
		parametri = apu.get(5); // Valmis ohjelmakoodi, mik�li teht�v�ss� on tarve
		
		// Tarkistetaan sis�lt��k� avainsanat-muuttuja 0, 1 vai enemm�n sanoja ja asetetaan avainsana(t) tauluun
		if (avainsanat != null) {
			if (avainsanat.contains(",")) {
				as = avainsanat.split(","); // Alustetaan avainsanat taulukkoon
			}
			else {
				as = new String[1];
				as[0] = avainsanat;
			}
		}
		else {
			as = new String[0];
		}
		
		tehtAnto.setValue(tehtavanAnto);
	}
	
	/**
	 * Metodi arvioi k�ytt�j�n antaman sy�tteen tietokannassa olevaan tulosteeseen.
	 * @return totuusarvo vastaako tekstikent�n sy�te tietokannan kentt��.
	 */
	public boolean arvioiVastaus() {
		boolean totuus = false;
		if (tulosteAlue.getValue().equals(oikeaTuloste)) {
			totuus = true;
		}
		return totuus;
	}
	
	/**
	 * Metodi tarkistaa, onko parametrina annetussa tekstialueessa mit��n sis�lt��.
	 * @param ta
	 * @return totuusarvo onko tekstialueessa sis�lt��
	 */
	public boolean tarkistaKentta(TextArea ta) {
		if (ta.getValue() == "") {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodi tarkistaa k�ytt�j�n sy�tt�m�n ohjelman taulusta l�ytyvi� parametritietoja vastaan.
	 * @param ta
	 * @return totuusarvo l�ytyyk� vastauksesta tarvittavat osat
	 */
	public boolean tarkistaAvainsanat(TextArea ta, String[] as) {
		boolean totuus = true;
		if (as.length == 0) {
			return true;
		}
		for (int i = 0; i < as.length; i++) {
			if (ta.getValue().contains(as[i])) {
				totuus = true;
			}
			else {
				return false;
			}
		}
		return totuus;
	}
}
