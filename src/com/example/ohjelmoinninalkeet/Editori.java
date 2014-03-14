package com.example.ohjelmoinninalkeet;

//import com.sun.nio.sctp.Notification;
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
	private String tehtavaTyyppi = "";
	private Tietokanta db;
	private String tehtavanAnto;
	private String vastaus;
	private String oikeaTuloste;

	public Editori() {
		initDB();
		initLayout();
	}
	
	public void initLayout() {
		
		tekstikentta.setWidth(80, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		tulosteAlue.setWidth(80, TextArea.Unit.PERCENTAGE);
		tulosteAlue.setRows(30);
		tulosteAlue.setStyleName("tulosteStyle");
		tulosteAlue.setEnabled(false);
		otsikko.setContentMode(ContentMode.HTML);
		suorita.setStyleName("suoritaStyle");
		arvioi.setStyleName("arvioiStyle");
		takaisin.setStyleName("takaisinStyle");
		tehtAnto.setStyleName("tehtAntoStyle");
		
		HorizontalLayout ylaHlay = new HorizontalLayout();
		ylaHlay.setWidth("100%");
		HorizontalLayout keskiHlay = new HorizontalLayout();
		keskiHlay.setWidth("100%");
		HorizontalLayout alaHlay = new HorizontalLayout();
		
		ylaHlay.addComponent(takaisin);
		ylaHlay.addComponent(otsikko);
		
		keskiHlay.addComponent(tekstikentta);
		keskiHlay.addComponent(tulosteAlue);
		
		alaHlay.addComponent(suorita);
		alaHlay.addComponent(arvioi);
		
		vlay.addComponent(ylaHlay);
		vlay.addComponent(tehtAnto);
		vlay.addComponent(keskiHlay);
		vlay.addComponent(alaHlay);
		//vlay.addComponent(nappula);
		
		// Tapahtuman k‰sittely nappulalle
		suorita.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				tulosteAlue.setValue(toPython(tekstikentta.getValue()));
			}
		});
		
    	// Button, josta p‰‰see takaisin Python-n‰kym‰‰n.
		takaisin.addClickListener(new Button.ClickListener() {
		    public void buttonClick(ClickEvent event) {
		    	getUI().getNavigator().navigateTo(PythonUI.NAME);
		    }
		});
		
		setContent(vlay);
	}
	
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
            while (br.ready()) {
                paluu = paluu + br.readLine() + "\n";
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
			System.out.println(tehtavaTyyppi.toLowerCase());
		}
		
	}
	
	public void initDB() {
		db = new Tietokanta();
		tehtavanAnto = db.annaTehtava(tehtavaTyyppi).get(0);
		System.out.println(tehtavanAnto);
	}
}
