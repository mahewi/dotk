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
 * Luokka m‰‰rittelee ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Tutoriaali: Metodit"-painiketta.
 * Ikkuna sis‰lt‰‰ metodeita (Python) koskevan ohjeistuksen
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 *
 */
public class MetoditPopup extends Window {
	
	private Label tutoriaali = new Label("");
	private Button sulje = new Button("Sulje");
	private Panel tuto = new Panel();
	
	public MetoditPopup() {
        super("Tutoriaali: Metodit Pythonissa");
        center();
        
        setHeight("50%");
        setWidth("50%");
        
        VerticalLayout vlay = new VerticalLayout();
        
        tuto.setWidth("80%");
        tuto.setHeight("60%");
        tuto.setContent(tutoriaali);
        tutoriaali.setContentMode(ContentMode.HTML);
        
        String teksti = "<p>T‰ss‰ tutoriaalissa k‰yd‰‰n l‰pi perusteet omien metodien luomisesta. T‰m‰ eroaa huomattavasti siit‰, mit‰ aiemmin on tehty. Kaikki aiempien tutoriaalien "
        		+ "ohjelmat ovat seuranneet samaa kaavaa, eli ohjelma luetaan j‰rjestyksess‰ ylh‰‰lt‰ alasp‰in. Metodien avulla t‰m‰ j‰rjestys rikkoutuu, sill‰ nyt luomme "
        		+ "ohjelmakoodilohkoja (metodeja), joita voi kutsua miss‰ vaiheessa vain ohjelman suoritusta. Kun ohjelma kutsuu metodia, niin ohjelman suoritus siirtyy suoraan "
        		+ "metodin suoritukseen. Metodi esittely ohjelmassa tehd‰‰n seuraavan syntaksin mukaisesti: <i>def [metodin nimi]([argumentit]);</i><br></br> K‰yd‰‰n seuraavaksi "
        		+ "l‰pi kaksi hyvin yksinkertaista metodia, jotka tulostavat t‰ysin saman asian, mutta hieman eri tavalla. <br></br><i> def tulostaNimi(): <br>&nbsp;&nbsp;"
        		+ "print ''Mikko Mallikas'';<br>&nbsp;&nbsp;return;<br></br> def tulostaParametri(nimi): <br>&nbsp;&nbsp;print nimi;<br>&nbsp;&nbsp;return;<br></br>tulostaNimi();<br>"
        		+ "tulostaParametri(''Mikko Mallikas'');</i><br></br> Ensimm‰iseksi esitelty metodi <i>tulostaNimi()</i> ei sis‰ll‰ parametrej‰ ja sit‰ kutsuttaessa tulostuu "
        		+ "merkkijono 'Mikko Mallikas'. Toinen metodi <i>tulostaParametri(nimi)</i> sis‰lt‰‰ yhden parametrin, joten kutsuessa sit‰, tulee t‰m‰ huomioida. Jos t‰t‰ metodia "
        		+ "kutsuu ilman parametri‰, ohjelman suoritus kaatuu. Esimerkiss‰ metodia kutsutaan parametrill‰ 'Mikko Mallikas'. <br></br> Metodilla voi olla useampiakin parametrej‰ "
        		+ "ja metodia kutsuessa tulee huomioida parametrien j‰rjestys. T‰st‰ lyhyt esimerkki: <br></br><i>def tulostaKokoNimi(etu, suku):<br>&nbsp;&nbsp;print ''Hei '' + etu + "
        		+ "'' '' + suku + ''!'';<br>&nbsp;&nbsp;return;<br></br>etu = ''Mikko'';<br>suku = ''Mallikas'';<br>tulostaKokoNimi(etu,suku);</i><br></br> T‰m‰ ohjelma tulostaa "
        		+ "siis 'Hei Mikko Mallikas!'.<br></br> Muuttujaan voidaan asettaa suoraan metodin palauttama arvo. T‰m‰ tapahtuu metodissa olevan varatun sanan <i>return</i> avulla. "
        		+ "Esitet‰‰n t‰st‰ seuraavaksi yksinkertainen esimerkki, jossa muuttujan <i>x</i> arvoksi asetetaan 10: <br></br><i> def palautaKymmenen():<br>&nbsp;&nbsp;return 10;<br></br>"
        		+ "x = palautaKymmenen();</i></p>"; 
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
