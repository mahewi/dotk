package com.example.ohjelmoinninalkeet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Runo;

/**
 * Luokka m‰‰rittelee ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Tutoriaali: Muuttujat"-painiketta.
 * Ikkuna sis‰lt‰‰ Python-muuttujia koskevan ohjeistuksen
 * 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 */
public class MuuttujatPopup extends Window {
	
	private Label tutoriaali = new Label("");
	private Button sulje = new Button("Sulje");
	
	public MuuttujatPopup() {
        super("Tutoriaali: Muuttujat Pythonissa");
        center();
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout vlay = new VerticalLayout();
        
        tutoriaali.setContentMode(ContentMode.HTML);
        String teksti = "<p>Muuttujat ovat paikkoja/lokeroita, joihin tallennetaan tietoa. Tiedolla on aina jokin tyyppi. "
        		+ "Yleisimpi‰ tietotyyppej‰ ovat muun muassa: kokonaisluku (<i>int</i>), merkkijono (<i>String</i>), liukuluku (<i>float</i>) ja totuusarvo (<i>boolean</i>)."
        		+ " Seuraavassa esimerkiss‰ esitet‰‰n, miten edell‰ mainittuihin tietotyyppeihin asetetaan arvo ja miten asetetun arvon voi lukea muuttujasta."
        		+ "<br> </br> Asetetaan aluksi muuttujien arvot: <br></br> <i>kokonaisluku = 1;<br>merkkijono = ''Moi'';<br> liukuluku = 1.5;<br>totuusArvo = False;</i>"
        		+ "<br></br> Muuttujien arvon voi lukea seuraavalla tavalla:<br></br> <i>print kokonaisluku;<br>print merkkijono;</i><br></br> N‰m‰ kaksi rivi‰"
        		+ " tulostaisivat seuraavat rivit:<br></br> <i>1<br> Moi</i><br></br> Jos muuttuja halutaan yhdist‰‰ merkkijonon kanssa tulostusta varten, tulee "
        		+ "se muuttaa tekstimuotoon k‰ytt‰m‰ll‰ <i>str</i>-metodia. T‰m‰ tapahtuu seuraavalla tavalla. Metodeista kerrotaan tarkemmin metodeihin liittyv‰ss‰"
        		+ " tutoriaalissa.<br></br> <i>print ''Muuttujan 'totuusarvo' arvo on ''" + "+ str(totuusarvo);</i>" + "<br></br> T‰m‰ tulostaa seuraavan "
        		+ "rivin:<br></br> <i>Muuttujan 'totuusarvo' arvo on False</i><br></br> Muuttujien arvon voi muuttaa helposti asettamalla muuttujaan uusi arvo. "
        		+ "Toiseksi on hyv‰ muistaa, ett‰ Pythonissa muuttujien tyypit eiv‰t ole pysyvi‰, vaan muuttujan tyyppi m‰‰r‰ytyy siihen viimeeksi asetetun "
        		+ "arvon mukaan. T‰m‰ esitell‰‰n seuraavassa esimerkiss‰.<br></br> <i>testi = " + "''Mikko''" + ";" + "<br>testi = 2;<br> print testi;</i><br></br> T‰m‰ "
        		+ "siis tulostaa muuttujan testin arvon, joka on 2.<br></br> Viimeisen‰, mutta hyvinkin t‰rke‰n‰ asiana on muuttujien nime‰minen. T‰h‰n "
        		+ "liittyy muutamia ehdottomia s‰‰ntˆj‰. Muuttujan nimess‰ ei saa olla erikoismerkkej‰ (esim. skandeja, kysymysmerkki jne.), muuttujan "
        		+ "nimess‰ ei saa olla v‰lilyˆnti‰, vaan monisanainen muuttujan nimi tulee kirjoittaa jommalla kummalla seuraavaksi esitetyll‰ tyylill‰: "
        		+ "<i>nimiMuuttuja</i> tai <i>nimi_muuttuja</i>. Muuttujan nimi ei saa siis myˆsk‰‰n alkaa isolla kirjaimella eik‰ numerolla.</p>";
        tutoriaali.setValue(teksti);
        sulje.setStyleName(Runo.BUTTON_DEFAULT);
        sulje.setWidth("25%");
        vlay.addComponent(tutoriaali);
        vlay.addComponent(sulje);
        vlay.setComponentAlignment(sulje, Alignment.BOTTOM_CENTER);
        vlay.setMargin(true);
        setContent(vlay);
        
        // Tapahtuman k‰sittely sulje-painikkeelle. Napin painallus sulkee ikkunan.
        sulje.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
        
	}

}
