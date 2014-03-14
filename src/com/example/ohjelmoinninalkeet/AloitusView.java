package com.example.ohjelmoinninalkeet;

import com.vaadin.annotations.Theme;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
public class AloitusView extends Panel implements View {
	
	public static final String NAME = "";
	private Label otsikko = new Label("<h1 class='etuSivuOtsikko'>Ohjelmoinnin alkeet</h1>");
	private Label esittely = new Label("");
	private Label kielet = new Label("<h2>Valitse ohjelmointikieli</h2>");
	private Label tekijat = new Label("<i>(c) Marco Willgren & Tatu Sepp�-Lassila, 2014</i>");
	private Button python = new Button("Python");
	private Panel esittelyPaneeli = new Panel();

	public AloitusView() {
		initLayout();
	}
	
	public void initLayout() {
		otsikko.setContentMode(ContentMode.HTML);
		esittely.setContentMode(ContentMode.HTML);
		kielet.setContentMode(ContentMode.HTML);
		tekijat.setContentMode(ContentMode.HTML);
		
		String teksti = "<p><b class='esittelyotsikko'>Mik� on ohjelmoinnin alkeet -sovellus?</b> <br></br> Ohjelmoinnin alkeet -sovelluksessa on ohjelmoimisen perusteisiin liittyvi� teht�vi� ja ohjeita. "
				+ "Teht�v�t ovat suunnattu aloitteleville ohjelmoijille, eiv�tk� t�ten vaadi aiempaa ohjelmointiosaamista. Teht�vien yhteydess� on mahdollisuus selata aihealuetta koskevia oppaita/videoita ja onkin suotavaa "
				+ "tehd� n�in, mik�li oppimiskokemuksesta haluaa ottaa kaiken irti. <br></br>"
				+ "Haluttu ohjelmointikieli valitaan sivun oikeassa laidassa olevasta listasta (painamalla kielt� koskevaa painiketta (esim. 'Python')). Kielen valittua ohjelma avaa uuden n�kym�n, jossa "
				+ "k�ytt�j� p��see valitsemaan halutun teht�v�tyypin tai tutoriaalin. Kaikki sovelluksen teht�v�t vaativat ohjelmakoodin kirjoittamista.<br></br> Teht�v�n palauttamisen j�lkeen k�ytt�j� saa "
				+ "v�litt�m�sti palautteen teht�v�st�. Palautteen saamisen j�lkeen k�ytt�j� voi joko palata takaisin korjaamaan vastaustaan tai siirty� katsomaan mallivastauksen. Mallivastaus sis�lt�� niin ohjelmakoodiosan "
				+ "kuin visuaalisen esityksen, mit� ohjelmassa k�yt�nn�ss� tapahtuu.<br></br>"
				+ "Onnea tuleviin koitoksiin!</p>";
		esittely.setValue(teksti);
		esittelyPaneeli.setWidth("60%");
		esittelyPaneeli.setHeight("60%");
		esittelyPaneeli.setContent(esittely);
		tekijat.setStyleName("tekijaStyle");
		python.setStyleName("pythonStyle");
		
		HorizontalSplitPanel split = new HorizontalSplitPanel();
		setContent(split);
		
		VerticalLayout vasenLayout = new VerticalLayout();
		VerticalLayout oikeaLayout = new VerticalLayout();
		
		split.addComponent(vasenLayout);
		split.addComponent(oikeaLayout);
		split.setLocked(true);
		split.setSplitPosition(60);
		
		python.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(PythonUI.NAME);
		    }
		});
		
		vasenLayout.addComponent(otsikko);
		vasenLayout.addComponent(esittelyPaneeli);
		oikeaLayout.addComponent(kielet);
		oikeaLayout.addComponent(python);
		oikeaLayout.addComponent(tekijat);
		
		vasenLayout.setComponentAlignment(esittelyPaneeli, Alignment.MIDDLE_CENTER);
		oikeaLayout.setComponentAlignment(python, Alignment.MIDDLE_CENTER);
	}
	
	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
	
}

