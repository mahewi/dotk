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

public class MuuttujatPopup extends Window {
	
	private Label tutoriaali = new Label("");
	private Button sulje = new Button("Sulje");
	private Panel tuto = new Panel();
	
	public MuuttujatPopup() {
        super("Tutoriaali: Muuttujat Pythonissa");
        center();
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout vlay = new VerticalLayout();
        
        tuto.setWidth("80%");
        tuto.setHeight("60%");
        tuto.setContent(tutoriaali);
        tutoriaali.setContentMode(ContentMode.HTML);
        String teksti = "<p>Muuttujat ovat paikkoja/lokeroita, joihin tallennetaan tietoa. Tiedolla on aina jokin tyyppi. "
        		+ "Yleisimpiä tietotyyppejä ovat muun muassa: kokonaisluku (<i>int</i>), merkkijono (<i>String</i>), liukuluku (<i>float</i>) ja totuusarvo (<i>boolean</i>)."
        		+ " Seuraavassa esimerkissä esitetään, miten edellä mainittuihin tietotyyppeihin asetetaan arvo ja miten asetetun arvon voi lukea muuttujasta."
        		+ "<br> </br> Asetetaan aluksi muuttujien arvot: <br></br> <i>kokonaisluku = 1;<br>merkkijono = Moi;<br> liukuluku = 1.5;<br>totuusArvo = False;</i>"
        		+ "<br></br> Muuttujien arvon voi lukea seuraavalla tavalla:<br></br> <i>print kokonaisluku;<br>print merkkijono;</i><br></br> Nämä kaksi riviä"
        		+ " tulostaisivat seuraavat rivit:<br></br> <i>1<br> Moi</i><br></br> Jos muuttuja halutaan yhdistää merkkijonon kanssa tulostusta varten, tulee "
        		+ "se muuttaa tekstimuotoon käyttämällä <i>str</i>-metodia. Tämä tapahtuu seuraavalla tavalla. Metodeista kerrotaan tarkemmin metodeihin liittyvässä"
        		+ " tutoriaalissa.<br></br> <i>print Muuttujan 'totuusarvo' arvo on " + "+ str(totuusarvo);</i>" + "<br></br> Tämä tulostaa seuraavan "
        		+ "rivin:<br></br> <i>Muuttujan 'totuusarvo' arvo on False</i><br></br> Muuttujien arvon voi muuttaa helposti asettamalla muuttujaan uusi arvo. "
        		+ "Toiseksi on hyvä muistaa, että Pythonissa muuttujien tyypit eivät ole pysyviä, vaan muuttujan tyyppi määräytyy siihen viimeeksi asetetun "
        		+ "arvon mukaan. Tämä esitellään seuraavassa esimerkissä.<br></br> <i>testi = " + "Mikko" + ";" + "<br>testi = 2;<br> print testi;</i><br></br> Tämä "
        		+ "siis tulostaa muuttujan testin arvon, joka on 2.<br></br> Viimeisenä, mutta hyvinkin tärkeänä asiana on muuttujien nimeäminen. Tähän "
        		+ "liittyy muutamia ehdottomia sääntöjä. Muuttujan nimessä ei saa olla erikoismerkkejä (esim. skandeja, kysymysmerkki jne.), muuttujan "
        		+ "nimessä ei saa olla välilyöntiä, vaan monisanainen muuttujan nimi tulee kirjoittaa jommalla kummalla seuraavaksi esitetyllä tyylillä: "
        		+ "<i>nimiMuuttuja</i> tai <i>nimi_muuttuja</i>. Muuttujan nimi ei saa siis myöskään alkaa isolla kirjaimella eikä numerolla.</p>";
        tutoriaali.setValue(teksti);
        sulje.setStyleName("suljeStyle");
        
        vlay.addComponent(tutoriaali);
        vlay.addComponent(sulje);
        vlay.setComponentAlignment(sulje, Alignment.BOTTOM_CENTER);
        vlay.setMargin(true);
        setContent(vlay);
        
        sulje.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
        
	}

}
