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
 * Luokka m‰‰rittelee ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Tutoriaali: Toistorakenteet"-painiketta.
 * Ikkuna sis‰lt‰‰ toistorakenteita (Python) koskevan ohjeistuksen
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 *
 */
public class ToistorakenteetPopup extends Window {
	
	private Label tutoriaali = new Label("");
	private Button sulje = new Button("Sulje");
	private Panel tuto = new Panel();
	
	public ToistorakenteetPopup() {
        super("Tutoriaali: Toistorakenteet Pythonissa");
        center();
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout vlay = new VerticalLayout();
        
        tuto.setWidth("80%");
        tuto.setHeight("60%");
        tuto.setContent(tutoriaali);
        tutoriaali.setContentMode(ContentMode.HTML);
        
        String teksti = "<p>Toistorakenne on rakenne, jossa toistetaan jotain tietty‰ ohjelmakoodia niin pitk‰‰n, kunnes annettu ehto ei en‰‰ toteudu. T‰m‰n rakenteen avulla voimme "
        		+ "muun muassa v‰ltt‰‰ joutumasta kirjoittamaan samaa asiaa yh‰ uudelleen ohjelmakoodiin ja toistorakenteen avulla on mielek‰st‰ k‰yd‰ l‰pi tallennusrakenteita, kuten "
        		+ "taulukoita. Taulukoita ei k‰sitell‰ t‰ss‰ tutoriaalissa, vaan k‰sittelyn taso pysyy yksinkertaisissa asioissa. Seuraavassa esimerkiss‰ k‰yd‰‰n l‰pi kaksi mahdollista "
        		+ "tapaa tulostaa kokonaisluvut yhdest‰ viiteen. Toisessa tavassa k‰ytet‰‰n hyv‰ksi toistorakennetta (<i>while</i>-rakennetta)<br></br> <b>Tapa 1:</b><br> <i>print 1;<br>"
        		+ "print 2;<br>print 3;<br>print 4;<br>print 5;</i><br><br> <b>Tapa 2:</b><br><i> apu = 1; <br></br> while apu <= 5:<br> &nbsp;&nbsp;print apu;<br>&nbsp;&nbsp;apu = apu + 1;</i><br></br> "
        		+ "Tapa 2 esittelee siis toistorakenteen k‰yttˆ‰. Siin‰ varatun sanan <i>while</i> j‰lkeen kirjoitetaan haluttu ehto, joka tarkistetaan heti kun toistorakenteeseen "
        		+ "p‰‰st‰‰n ja lis‰ksi aina, kun yksi kierros toistorakenteessa on suoritettu. Mik‰li ehto toteutuu, niin ohjelman suoritus siirtyy lohkon sis‰lle ja heti, kun ehto ei "
        		+ "en‰‰ toteutu, ohjelman suoritus ''hypp‰‰'' toistorakenteen yli. <br></br> Toistorakenteita toteuttaessa tulee pit‰‰ huoli siit‰, ett‰ ohjelman suoritus p‰‰ttyy "
        		+ "j‰rkev‰ss‰ ajassa, eik‰ toistorakenne j‰‰ pyˆrim‰‰n loputtomiin/‰‰rettˆm‰n pit‰‰n. T‰llainen loputon silmukka saadaan aikaseksi silloin, kun toistorakenteen ehto "
        		+ "toteutuu aina kun se tarkistetaan. T‰st‰ esimerkki: <br></br><i>apu = 0;<br></br> while apu != 1:<br>&nbsp;&nbsp; print ''Loputon silmukka'';</i> <br></br> Tehd‰‰n viel‰ "
        		+ "lopuksi hieman monimutkaisempi ohjelma, joka laskee toistorakenteen sis‰ll‰ kahden muuttujan tuloa. Toistorakenne sis‰lt‰‰ toistoehdon lis‰ksi ehtolauselohkon, "
        		+ "jossa tarkastetaan muuttujan arvoa jokaisella toistokierroksella. Kun ehto ei en‰‰ toteudu, ohjelma siirtyy <i>else</i>-lohkoon, jossa silmukan suoritus pakotetaan "
        		+ "loppumaan varatun sanan <i>break</i> avulla. <br></br><i> apu = 1;<br> apu2 = 10; <br></br> while apu < 100000: <br> &nbsp;&nbsp; if apu != 1000: <br> "
        		+ "&nbsp;&nbsp;&nbsp;&nbsp; apu = apu * apu2;<br> &nbsp;&nbsp;&nbsp;&nbsp; print apu;<br> &nbsp;&nbsp;else:<br>&nbsp;&nbsp;&nbsp;&nbsp;print ''Muuttujan 'apu' arvo on "
        		+ "nyt 1000, joten poistutaan silmukasta.'';<br>&nbsp;&nbsp;&nbsp;&nbsp;break;</i> <br></br> T‰ss‰ tutoriaalissa k‰siteltiin vain <i>while</i> tyyppist‰"
        		+ " toistorakennetta. Python-kieless‰ on myˆs mahdollista k‰ytt‰‰ ns. <i>for</i>-rakennetta, joka on todella k‰yttˆkelpoinen etenkin tilanteissa, kun halutaan"
        		+ " k‰yd‰ l‰pi taulukoita ja muita tallennusrakenteita. <i>for</i>-rakenteen syntaksia ei k‰yd‰ t‰ss‰ tutoriaalissa tarkemmin l‰pi.</p>";
        tutoriaali.setValue(teksti);
        sulje.setStyleName(Runo.BUTTON_DEFAULT);
        sulje.setWidth("25%");
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
