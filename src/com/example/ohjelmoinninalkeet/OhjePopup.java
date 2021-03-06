package com.example.ohjelmoinninalkeet;

import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Runo;

@SuppressWarnings("serial")
/**
 * Luokka m��rittelee ponnahdusikkunan, joka aukeaa k�ytt�j�n painaessa "OHJE"-painiketta.
 * Ikkuna sis�lt�� Editori-n�kym�n ohjeet, jotka kertovat kuinka vastaus tulee kirjoittaa ja miten se arvioidaan. 
 * 
 * @author Marco Willgren & Tatu Sepp�-Lassila
 */
public class OhjePopup extends Window {
	
	public OhjePopup() {
		super("Ohje teht�v�n tekemiseen");
		center();
		
		Button sulje = new Button("Sulje");
        sulje.setStyleName(Runo.BUTTON_DEFAULT);
        sulje.setWidth("25%");
        Label ohje = new Label("");

        ohje.setContentMode(ContentMode.HTML);
        String ohjeistus = "<p>� Vastaa teht�v��n t�ysin teht�v�nannon mukaisesti <br>&nbsp;&nbsp;&nbsp;&nbsp; � Oikea tuloste v��r�ss� muodossa tulkitaan v��r�ksi "
        		+ "vastaukseksi! <br></br> � Ohjelma kirjoitetaan vasemman puoleiseen teksialueeseen <br>&nbsp;&nbsp;&nbsp;&nbsp; � Kun olet mielest�si valmis, paina 'Suorita'-"
        		+ "painiketta.<br></br>� Mik�li ohjelma ei avaa virheilmoitusta, n�et ohjelmasi tulostuksen oikeanpuoleisessa tekstialueessa. Paina t�m�n j�lkeen 'Arvioi'-painiketta."
        		+ "<br>&nbsp;&nbsp;&nbsp;&nbsp; � Kirjoittamasi ohjelman oikeellisuus arvioidaan ja ruudulle ilmestyy v�lit�n palaute suorituksesta. </p>";
        ohje.setValue(ohjeistus);
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout sisalto = new VerticalLayout();
        sisalto.setSizeFull();
        sisalto.addComponent(ohje);
        sisalto.addComponent(sulje);
        sisalto.setComponentAlignment(sulje, Alignment.BOTTOM_CENTER);
        sisalto.setMargin(true);
        setContent(sisalto);
        
        // Tapahtuman k�sittely sulje-painikkeelle. Napin painallus sulkee ikkunan.
        sulje.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
	}

}
