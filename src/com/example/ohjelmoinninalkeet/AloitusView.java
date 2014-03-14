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
	private Label tekijat = new Label("<i>(c) Marco Willgren & Tatu Seppä-Lassila, 2014</i>");
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
		
		String teksti = "<p><b class='esittelyotsikko'>Mikä on ohjelmoinnin alkeet -sovellus?</b> <br></br> Ohjelmoinnin alkeet -sovelluksessa on ohjelmoimisen perusteisiin liittyviä tehtäviä ja ohjeita. "
				+ "Tehtävät ovat suunnattu aloitteleville ohjelmoijille, eivätkä täten vaadi aiempaa ohjelmointiosaamista. Tehtävien yhteydessä on mahdollisuus selata aihealuetta koskevia oppaita/videoita ja onkin suotavaa "
				+ "tehdä näin, mikäli oppimiskokemuksesta haluaa ottaa kaiken irti. <br></br>"
				+ "Haluttu ohjelmointikieli valitaan sivun oikeassa laidassa olevasta listasta (painamalla kieltä koskevaa painiketta (esim. 'Python')). Kielen valittua ohjelma avaa uuden näkymän, jossa "
				+ "käyttäjä pääsee valitsemaan halutun tehtävätyypin tai tutoriaalin. Kaikki sovelluksen tehtävät vaativat ohjelmakoodin kirjoittamista.<br></br> Tehtävän palauttamisen jälkeen käyttäjä saa "
				+ "välittömästi palautteen tehtävästä. Palautteen saamisen jälkeen käyttäjä voi joko palata takaisin korjaamaan vastaustaan tai siirtyä katsomaan mallivastauksen. Mallivastaus sisältää niin ohjelmakoodiosan "
				+ "kuin visuaalisen esityksen, mitä ohjelmassa käytännössä tapahtuu.<br></br>"
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

