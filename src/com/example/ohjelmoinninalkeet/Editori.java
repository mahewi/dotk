package com.example.ohjelmoinninalkeet;

//import com.sun.nio.sctp.Notification;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Label;

import java.lang.StringBuilder;


import java.io.*;

public class Editori extends Panel implements View {

	public static final String NAME = "editoriView";
	
	// Komponentit
	
	VerticalLayout vlay = new VerticalLayout();
	TextArea tekstikentta = new TextArea("Koodia tähän");
	Button nappula = new Button("Arvioi");
	TextArea label = new TextArea();
	Link lnk = new Link("BÄK!:D", new ExternalResource("#!" + PythonUI.NAME));
	
	public Editori() {
		initLayout();
	}
	
	public void initLayout() {
		
		tekstikentta.setWidth(50.0f, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		//label.setContentMode(ContentMode.HTML);
		vlay.addComponent(lnk);
		vlay.addComponent(tekstikentta);
		vlay.addComponent(nappula);
		vlay.addComponent(label);
		
		// Tapahtuman käsittely nappulalle
		
		nappula.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				label.setValue(toPython(tekstikentta.getValue()));
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
		// TODO Auto-generated method stub
		
	}
}
