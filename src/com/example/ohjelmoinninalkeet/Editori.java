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


public class Editori extends Panel implements View {

	public static final String NAME = "editoriView";
	
	// Komponentit
	VerticalLayout vlay = new VerticalLayout();
	TextArea tekstikentta = new TextArea("");
	Button nappula = new Button("Arvioi");
	Button takaisin = new Button("Takaisin");
	TextArea tulosteAlue = new TextArea();
	Label otsikko = new Label("");

	public Editori() {
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
		nappula.setStyleName("arvioiStyle");
		takaisin.setStyleName("takaisinStyle");
		HorizontalLayout ekaHlay = new HorizontalLayout();
		ekaHlay.setWidth("100%");
		HorizontalLayout tokaHlay = new HorizontalLayout();
		tokaHlay.setWidth("100%");
		
		ekaHlay.addComponent(takaisin);
		ekaHlay.addComponent(otsikko);
		
		tokaHlay.addComponent(tekstikentta);
		tokaHlay.addComponent(tulosteAlue);
		
		vlay.addComponent(ekaHlay);
		vlay.addComponent(tokaHlay);
		//vlay.addComponent(tekstikentta);
		vlay.addComponent(nappula);
		//vlay.addComponent(label);
		//vlay.setComponentAlignment(tekstikentta, Alignment.MIDDLE_LEFT);
		
		// Tapahtuman k�sittely nappulalle
		nappula.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				tulosteAlue.setValue(toPython(tekstikentta.getValue()));
			}
		});
		
    	// Button, josta p��see takaisin Python-n�kym��n.
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
			Notification.show("Nyt sattui jotain j�nn��!");
		}
		else {
			otsikko.setValue("<h1 class='editoriOtsikko'>" + event.getParameters() + "</h1>");
		}
		
	}
}
