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

@SuppressWarnings("serial")
/**
 * Luokka m‰‰rittelee ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Tutoriaali: Ehtolauseet"-painiketta.
 * Ikkuna sis‰lt‰‰ ehtolauseita (Pythonissa) koskevan ohjeistuksen
 * 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 */
public class EhtolauseetPopup extends Window {
	
	private Label tutoriaali = new Label("");
	private Button sulje = new Button("Sulje");
	private Panel tuto = new Panel();
	
	public EhtolauseetPopup() {
        super("Tutoriaali: Ehtolauseet Pythonissa");
        center();
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout vlay = new VerticalLayout();
        
        tuto.setWidth("80%");
        tuto.setHeight("60%");
        tuto.setContent(tutoriaali);
        tutoriaali.setContentMode(ContentMode.HTML);
        
        String teksti = "<p>Ehtolauseita tarvitaan silloin, kun ohjelmassa on tilanne, mik‰ vaatii valintaa jonkin ehdon (tai totuusarvon) mukaisesti. Ehtolauseiden avulla ohjelman "
        		+ "suoritus voidaan siis jakaa useampaan eri haaraan. Pythonissa ehtolauseiden aloittava 'varattu' sana on <i>if</i>. Muuttujien arvoja ja/tai suoraan annettuja arvoja "
        		+ "vertaillaan k‰ytt‰en Pythonin vertailuoperaattoreita: <br></br> > (suurempi kuin) <br> >= (suurempi tai yht‰suuri kuin) <br> < (pienempi kuin) <br> <= (pienempi tai "
        		+ "yht‰suuri kuin) <br> == (yht‰suuri kuin) <br> != (erisuuri kuin) <br></br> Ehtolauseiden syntaksissa tulee tiet‰‰ myˆs se, ett‰ <i>if</i>-lause loppuu kaksoipisteeseen "
        		+ "eik‰ puolipisteeseen niin kuin aiemmin on totuttu. T‰m‰ johtuu siit‰, ett‰ ehtolause ei p‰‰ty <i>if</i>-ehdon j‰lkeen. Lis‰ksi ehtolauseen j‰lkeen suoritettava "
        		+ "osio tulee sisent‰‰. Seuraavaksi esitell‰‰n hyvin yksinkertainen esimerkki ehtolauseesta Pythonissa: <br></br> <i>if 10 < 11: <br> &nbsp;&nbsp; print " + "''10 on pienempi kuin 11''" + ";</i> "
        		+ "<br></br> T‰m‰ ohjelma siis vertailee onko kokonaisluku 10 pienempi kuin 11, joka on siis tosi. T‰st‰ seuraa, ett‰ ohjelma tulostaa '10 on pienempi kuin 11'. Otetaan "
        		+ "seuraavaksi k‰sittelyyn hieman monimutkaisempi ehtolause: <br></br> <i>vertailtava = 10; <br></br> if vertailtava > 9: <br> &nbsp;&nbsp; print " + "''vertailtava on suurempi kuin 9''" + "; "
        		+ "<br> &nbsp;&nbsp; if vertailtava < 20: <br> &nbsp;&nbsp;&nbsp;&nbsp; print " + "''vertailtava on pienempi kuin 20. Se on siis jokin luku 9 ja 20 valissa''" + "; <br> &nbsp;&nbsp;&nbsp;&nbsp; "
        		+ "if vertailtava == 10: <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; print " + "''vertailtava on "
        		+ "arvoltaan 10!''" + "; </i><br></br> Esimerkiss‰ siis verrataan muuttujaan <i>vertailava</i> asetettua arvoa eri ehtolauseiden l‰pi. Koska t‰ss‰ tapauksessa kaikki ehdot "
        		+ "toteutuvat, niin ohjelma suoritetaan loppuun asti ja kaikki vaiheet tulostetaan. Ts. ehtolauseessa ei edet‰ en‰‰ mik‰li tarkasteltava ehto ei toteudu. <br></br> "
        		+ "T‰ss‰ tutoriaalissa tarkastellaan viel‰ lopuksi tilannetta, miss‰ ehtolauseeseen asetetaan suorituslohko mihin siirryt‰‰n siin‰ tilanteessa, kun ehtolause saa "
        		+ "totuusarvon <i>False</i>. T‰m‰n lohkon varattu sana Pythonissa on <i>else</i>. Seuraavaksi esitell‰‰n yksinkertainen esimerkki t‰llaisesta tilanteesta: <br></br>"
        		+ "<i>vertailtava = 10;<br></br> if vertailtava > 11:<br> &nbsp;&nbsp; print " + "''vertailtava on suurempi kuin 11''" + ";<br> else: <br> &nbsp;&nbsp; print " + "''vertailtava on pienempi kuin 11''" + ";</i><br></br>"
        		+ " </p>";
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
