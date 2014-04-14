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
import com.vaadin.ui.Label;
import com.vaadin.ui.themes.Runo;

import java.io.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
/**
 * Editori-luokka muodostaa n‰kym‰n, jossa k‰ytt‰j‰ p‰‰see vastaamaan Python aiheisiin teht‰v‰‰n.
 * N‰kym‰ss‰ on toiminnot vastauksen suorittamiseen Python-k‰‰nt‰j‰n l‰pi sek‰ arvioiminen vertaamalla
 * sit‰ oikeaa tulostetta vastaan.
 *  
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 */
public class Editori extends Panel implements View {
	
	// Muuttuja jota Navigator-olio k‰ytt‰‰ n‰kym‰n identifioimiseen
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
	private Label tulosteOtsikko = new Label("<p class='teht'>Ohjelman tulostus (Python-k‰‰nt‰j‰n l‰pi ajettuna)</p>");
	private String tehtavaTyyppi = "";
	private String tehtavanAnto, malliVastaus, videoLinkki, oikeaTuloste, avainsanat, parametri;
	private Tietokanta db;
	private String[] as, tulosteRivit, malliv, parametrit;
	
	public Editori() {
		initLayout();
	}
	
	// Asetetaan komponentit n‰kym‰‰n ja rekisterˆid‰‰n toiminnallisuuksia.
	public void initLayout() {
		vlay.setSizeFull();
		
		otsikko.setContentMode(ContentMode.HTML);		
		ohje.setStyleName("ohjeStyle");
		takaisin.setWidth("20em");
		takaisin.setStyleName(Runo.BUTTON_DEFAULT);
		tekstikentta.setWidth(80, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(16);
		tehtAnto.setStyleName("tehtAntoStyle");
		tehtAnto.setContentMode(ContentMode.HTML);
		tulosteAlue.setWidth(80, TextArea.Unit.PERCENTAGE);
		tulosteAlue.setRows(16);
		tulosteAlue.setStyleName("tulosteStyle");
		tulosteAlue.setEnabled(false);
		tulosteOtsikko.setStyleName("tulosteOtsikkoStyle");
		tulosteOtsikko.setContentMode(ContentMode.HTML);
		arvioi.setEnabled(false);
		arvioi.setStyleName("arvioiStyle");
		suorita.setStyleName("suoritaStyle");
		naytaVastaus.setEnabled(false);
		naytaVastaus.setStyleName("naytaVastausStyle");
		
		// M‰‰ritet‰‰n VerticalLayoutin sis‰‰n tulevat Layoutit
		HorizontalLayout ylaHlay = new HorizontalLayout();
		HorizontalLayout alempiYlaHlay = new HorizontalLayout();
		HorizontalLayout ylaOtsikkoHlay = new HorizontalLayout();
		HorizontalLayout ylaKeskiHlay = new HorizontalLayout();
		HorizontalLayout keskiHlay = new HorizontalLayout();
		HorizontalLayout alaHlay = new HorizontalLayout();
		
		// M‰‰ritet‰‰n layouttien leveydet
		ylaOtsikkoHlay.setWidth("100%");
		ylaKeskiHlay.setWidth("100%");
		keskiHlay.setWidth("100%");
		alaHlay.setWidth("40%");
		
		// Asetetaan komponentit m‰‰riteltyihin layoutteihin
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
		
		// Komponenttien asettelu layoutissa
		keskiHlay.setComponentAlignment(tekstikentta, Alignment.MIDDLE_CENTER);
		keskiHlay.setComponentAlignment(tulosteAlue, Alignment.MIDDLE_CENTER);
		
		// Asetetaan eri layoutit oikeassa j‰rjestyksess‰ VerticalLayoutin sis‰lle
		vlay.addComponent(ylaHlay);
		vlay.addComponent(alempiYlaHlay);
		vlay.addComponent(ylaOtsikkoHlay);
		vlay.addComponent(ylaKeskiHlay);
		vlay.addComponent(keskiHlay);
		vlay.addComponent(alaHlay);

		// Tapahtuman k‰sittely suorita-napille
		suorita.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (checkImport(tekstikentta) == true) {
					tulosteAlue.setValue(toPython(tekstikentta.getValue()));
					if (tarkistaKentta(tekstikentta) == true && tarkistaKentta(tulosteAlue) == true) {
						arvioi.setEnabled(true);  // Napin painalluksen j‰lkeen aktivoidaan "Arvioi"-painike
					}
					else if (tarkistaKentta(tekstikentta) == false) {
						Notification.show("VIRHE!", "Et voi suorittaa ohjelmaa, jota ei ole olemassa!", Notification.Type.ERROR_MESSAGE);
					}
					else {
						Notification.show("VIRHE!", "Ohjelmasi ei mennyt k‰‰nt‰j‰st‰ l‰pi!" + "\n\n" + "K‰‰nt‰j‰n antama virheilmoitus:" + "\n\n"
					    + getErrorCode(tekstikentta.getValue()), Notification.Type.ERROR_MESSAGE);
					}
				}
				else {
					Notification.show("Importing isn't necessary! ;)", Notification.Type.ERROR_MESSAGE);
				}
			}
		});
		
		// Tapahtuman k‰sittely arvioi-napille. Napin painallus avaa ponnahdusikkunan
		// ,jonka sis‰ltˆ riippu vastauksen oikeellisuudesta.
		arvioi.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (arvioiVastaus()) {
					PalautePopup palaute = new PalautePopup("Vastaus oikein!");
					UI.getCurrent().addWindow(palaute);
				}
				else {
					PalautePopup palaute = new PalautePopup("Vastaus v‰‰rin!");
					UI.getCurrent().addWindow(palaute);
				}
				arvioi.setEnabled(false);  // Napin painamisen j‰lkeen se disabloituu
				naytaVastaus.setEnabled(true);
			}
		});
		
    	// Nappi, josta p‰‰see takaisin Python-n‰kym‰‰n. 
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	tulosteAlue.setValue("");
		    	tekstikentta.setValue("");
		    	getUI().getNavigator().navigateTo(PythonUI.NAME);
		    }
		});
		
    	// Nappi, josta aukeaa ohjen‰kym‰
		ohje.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	OhjePopup o = new OhjePopup();
		    	UI.getCurrent().addWindow(o);
		    }
		});
		
    	// Nappi, josta aukeaa mallivastaus-n‰kym‰
		// Testivideolinkki: https://www.youtube.com/v/mjQyXmlo46U&feature=youtu.be
		naytaVastaus.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	Mallivastaus mv = new Mallivastaus(malliv, tehtavanAnto, videoLinkki);
		    	UI.getCurrent().addWindow(mv);
		    	naytaVastaus.setEnabled(false);
		    }
		});
		
		setContent(vlay); // Asetetaan lopuksi n‰kym‰ paneelin sis‰lle
	}
	
	/**
	 * Metodi vie k‰ytt‰j‰n antaman syˆtteen Python-k‰‰nt‰j‰n l‰pi. 
	 * @param code
	 * @return K‰ytt‰j‰n kirjoittaman ohjelmakoodin tuloste Python k‰‰nt‰j‰n l‰pi ajettuna
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
	
	/**
	 * Metodi vie k‰ytt‰j‰n syˆtt‰m‰n ohjelman/syˆtteen Python-k‰‰nt‰j‰n l‰pi
	 * @param code
	 * @return K‰‰nt‰j‰n antama virhekoodi
	 */
	public String getErrorCode(String code) {
		try {
			BufferedWriter out = new BufferedWriter(new FileWriter("test.py"));
			out.write(code);
			out.close();

			Runtime r = Runtime.getRuntime();
            Process p = r.exec("python test.py");
            BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
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
			Notification.show("Nyt sattui jotain j‰nn‰‰!");
		}
		else {
			otsikko.setValue("<h1 class='editoriOtsikko'>" + event.getParameters() + "</h1>");
			tehtavaTyyppi = event.getParameters().toLowerCase();
			initDB();
		}
	}
	
	/**
	 * Metodi luo tietokantaolio ja -yhteyden. Satunnainen k‰ytt‰j‰n aiheen mukainen 
	 * teht‰v‰ haetaan tietokannasta.
	 */
	public void initDB() {
		db = new Tietokanta();
		ArrayList<String> apu = new ArrayList<String>();
		apu = db.annaTehtava(tehtavaTyyppi);
		tehtavanAnto = "Teht‰v‰: " + apu.get(0);
		oikeaTuloste = apu.get(1); // Teht‰v‰n oikea tuloste
		malliVastaus = apu.get(2); // Mallivastaus tekstimuodossa
		videoLinkki = apu.get(3); // Linkki mallivastausvideoon
		avainsanat = apu.get(4); // Oikeasta vastauksesta t‰ytyy lˆyty‰
		parametri = apu.get(5); // Valmis ohjelmakoodi, mik‰li teht‰v‰ss‰ on tarve
		
		alustaTuloste();
		alustaAvainsanat();
		alustaMallivastaus();
		alustaParametrit();
		
		tehtAnto.setValue("<p class='teht'>" + tehtavanAnto + "</p>" );
	}
	
	/**
	 * Metodi arvioi k‰ytt‰j‰n antaman syˆtteen tietokannassa olevaan tulosteeseen.
	 * @return totuusarvo vastaako tekstikent‰n syˆte tietokannan kentt‰‰.
	 */
	public boolean arvioiVastaus() {
		String apu = "";
		boolean totuus = false;
		for (int i = 0; i < tulosteRivit.length; i++) {
			if (i == tulosteRivit.length - 1) {
				apu += tulosteRivit[i];
			}
			else {
				apu += tulosteRivit[i] + "\n";
			}

		}
		
		// Tarkistetaan, ett‰ tuloste vastaa kannasta haettua tulostetta.
		if (tulosteAlue.getValue().equals(apu)) {
			totuus = true;
		}
		else {
			return false;		
		}
		
		// Tarkistetaan, ett‰ k‰ytt‰j‰n syˆtt‰m‰st‰ ohjelmasta lˆytyy tarvittavat avainsanat
		if (tarkistaAvainsanat(tekstikentta, as)) {
			totuus = true;
		}
		else {
			return false;
		}
		
		return totuus;
	}
	
	/**
	 * Metodi tarkistaa, onko parametrina annetussa tekstialueessa mit‰‰n sis‰ltˆ‰.
	 * @param ta
	 * @return totuusarvo onko tekstialueessa sis‰ltˆ‰
	 */
	public boolean tarkistaKentta(TextArea ta) {
		if (ta.getValue() == "") {
			return false;
		}
		return true;
	}
	
	/**
	 * Metodi alustaa tietokannasta haetun teht‰v‰n tulosteen tauluun
	 */
	public void alustaTuloste() {
		// Alustetaan ohjelma tuloste tauluun.
		if (oikeaTuloste.contains("/n")) {
			tulosteRivit = oikeaTuloste.split("/n");
		}
		else {
			tulosteRivit = new String[1];
			tulosteRivit[0] = oikeaTuloste;
		}
	}
	
	/**
	 * Metodi alustaa tietokannasta haetun teht‰v‰n avainsanat tauluun
	 */
	public void alustaAvainsanat() {
		// Tarkistetaan sis‰lt‰‰kˆ avainsanat-muuttuja 0, 1 vai enemm‰n sanoja ja asetetaan avainsana(t) tauluun
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
	}
	
	/**
	 * Alustetaan tietokannasta haetun teht‰v‰n mallivastauksen tauluun
	 */
	public void alustaMallivastaus() {
		// Alustetaan ohjelma tuloste tauluun.
		if (malliVastaus.contains("/n")) {
			malliv = malliVastaus.split("/n");
		}
		else {
			malliv = new String[1];
			malliv[0] = malliVastaus;
		}
	}
	
	/**
	 * Metodi tarkistaa k‰ytt‰j‰n syˆtt‰m‰n ohjelman taulusta lˆytyvi‰ parametritietoja vastaan.
	 * @param ta
	 * @return totuusarvo lˆytyykˆ vastauksesta tarvittavat osat
	 */
	public boolean tarkistaAvainsanat(TextArea ta, String[] as) {
		boolean totuus = false;
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
			totuus = true;
		}
		return totuus;
	}
	
	/**
	 * Metodi alustaa tietokannasta haetun teht‰v‰n mahdolliset parametrit tauluun
	 */
	public void alustaParametrit() {
		if (parametri != null) {
			if (parametri.contains("/n")) {
				parametrit = parametri.split("/n"); // Alustetaan parametrit taulukkoon
			}
			else {
				parametrit = new String[1];
				parametrit[0] = parametri;
			}
		}
		else {
			parametrit = new String[0];
		}
		asetaParametrit(parametrit);
	}
	
	/**
	 * Asetetaan mahdolliset parametrit tekstikentt‰‰n.
	 * @param p
	 */
	public void asetaParametrit(String[] p) {
		String apu = "";
		if (p.length != 0) {
			for (int i = 0; i < p.length; i++) {
				if (i == p.length - 1) {
					apu += p[i];
				}
				else {
					apu += p[i] + "\n";
				}
			}
			tekstikentta.setValue(apu);
		}
	}
	
	public boolean checkImport(TextArea ta) {
		if (ta.getValue().contains("import")) {
			return false;
		}
		else {
			return true;
		}
	}
}
