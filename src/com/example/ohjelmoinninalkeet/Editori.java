package com.example.ohjelmoinninalkeet;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Label;

import java.io.*;
import java.util.ArrayList;

/**
 * Editori-luokka muodostaa n‰kym‰n, jossa k‰ytt‰j‰ p‰‰see vastaamaan Python aiheisiin teht‰v‰‰n.
 * N‰kym‰ss‰ on toiminnot vastauksen suorittamiseen Python-k‰‰nt‰j‰n l‰pi sek‰ arvioiminen vertaamalla
 * sit‰ oikeaa tulostetta vastaan. 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 *
 */
public class Editori extends Panel implements View {

	public static final String NAME = "editoriView";
	
	// Komponentit
	VerticalLayout vlay = new VerticalLayout();
	TextArea tekstikentta = new TextArea("");
	Button suorita = new Button("Suorita");
	Button arvioi = new Button("Arvioi");
	Button takaisin = new Button("Takaisin");
	TextArea tulosteAlue = new TextArea();
	Label otsikko = new Label("");
	Label tehtAnto = new Label("");
	Label tulosteOtsikko = new Label("Ohjelman tulostus (Python-k‰‰nt‰j‰n l‰pi ajettuna)");
	private String tehtavaTyyppi = "";
	private Tietokanta db;
	private String tehtavanAnto;
	private String malliVastaus;
	private String oikeaTuloste;

	public Editori() {
		initLayout();
	}
	
	// Asetetaan komponentit n‰kym‰‰n ja rekisterˆid‰‰n toiminnallisuuksia.
	public void initLayout() {
		tekstikentta.setWidth(80, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		tulosteAlue.setWidth(80, TextArea.Unit.PERCENTAGE);
		tulosteAlue.setRows(30);
		tulosteAlue.setStyleName("tulosteStyle");
		tulosteAlue.setEnabled(false);
		arvioi.setEnabled(false);
		otsikko.setContentMode(ContentMode.HTML);
		suorita.setStyleName("suoritaStyle");
		arvioi.setStyleName("arvioiStyle");
		takaisin.setStyleName("takaisinStyle");
		tehtAnto.setStyleName("tehtAntoStyle");
		tulosteOtsikko.setStyleName("tulosteOtsikkoStyle");
		
		// M‰‰ritet‰‰n VerticalLayoutin sis‰‰n tulevat Layoutit
		HorizontalLayout ylaHlay = new HorizontalLayout();
		ylaHlay.setWidth("100%");
		HorizontalLayout ylaOtsikkoHlay = new HorizontalLayout();
		ylaOtsikkoHlay.setWidth("100%");
		HorizontalLayout ylaKeskiHlay = new HorizontalLayout();
		ylaKeskiHlay.setWidth("100%");
		HorizontalLayout keskiHlay = new HorizontalLayout();
		keskiHlay.setWidth("100%");
		HorizontalLayout alaHlay = new HorizontalLayout();
		alaHlay.setWidth("20%");
		
		ylaHlay.addComponent(takaisin);
		ylaOtsikkoHlay.addComponent(otsikko);
		ylaKeskiHlay.addComponent(tehtAnto);
		ylaKeskiHlay.addComponent(tulosteOtsikko);
		
		keskiHlay.addComponent(tekstikentta);
		keskiHlay.addComponent(tulosteAlue);
		
		alaHlay.addComponent(suorita);
		alaHlay.addComponent(arvioi);
		
		keskiHlay.setComponentAlignment(tekstikentta, Alignment.MIDDLE_CENTER);
		keskiHlay.setComponentAlignment(tulosteAlue, Alignment.MIDDLE_CENTER);
		
		vlay.addComponent(ylaHlay);
		vlay.addComponent(ylaOtsikkoHlay);
		vlay.addComponent(ylaKeskiHlay);
		vlay.addComponent(keskiHlay);
		vlay.addComponent(alaHlay);

		// Tapahtuman k‰sittely suorita-napille
		suorita.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				tulosteAlue.setValue(toPython(tekstikentta.getValue()));
				arvioi.setEnabled(true);  // Napin painalluksen j‰lkeen aktivoidaan "Arvioi"-painike
			}
		});
		
		// Tapahtuman k‰sittely arvioi-napille
		arvioi.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				if (arvioiVastaus()) {
					Notification.show("VASTASIT OIKEIN");
				}
				else {
					Notification.show("VASTASIT VƒƒRIN");					
				}
				arvioi.setEnabled(false);  // Napin painamisen j‰lkeen se disabloituu
			}
		});
		
    	// Nappi, josta p‰‰see takaisin Python-n‰kym‰‰n.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(PythonUI.NAME);
		    }
		});
		
		setContent(vlay);
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
	 * 
	 */
	public void initDB() {
		db = new Tietokanta();
		tehtavaTyyppi = "muuttujat";
		ArrayList<String> apu = new ArrayList<String>();
		apu = db.annaTehtava(tehtavaTyyppi);
		tehtavanAnto = apu.get(0);
		oikeaTuloste = apu.get(1);
		malliVastaus = apu.get(2);
		tehtAnto.setValue(tehtavanAnto);
	}
	
	/**
	 * Metodi arvioi k‰ytt‰j‰n antaman syˆtteen tietokannassa olevaan tulosteeseen.
	 * @return totuusarvo vastaako tekstikent‰n syˆte tietokannan kentt‰‰.
	 */
	public boolean arvioiVastaus() {
		boolean totuus = false;
		if (tulosteAlue.getValue().equals(oikeaTuloste)) {
			totuus = true;
		}
		return totuus;
	}
}
