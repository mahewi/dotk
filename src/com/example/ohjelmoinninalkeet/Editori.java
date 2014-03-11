package com.example.ohjelmoinninalkeet;

//import com.sun.nio.sctp.Notification;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
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
	TextArea tekstikentta = new TextArea("Koodia t‰h‰n");
	Button nappula = new Button("Arvioi");
	Label label = new Label();
	Link lnk = new Link("Takaisin", new ExternalResource("#!" + PythonUI.NAME));
	Label tehtAnto = new Label("");

	public Editori() {
		initLayout();
	}
	
	public void initLayout() {
		
		tekstikentta.setWidth(50.0f, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		
		vlay.addComponent(lnk);
		vlay.addComponent(tehtAnto);
		vlay.addComponent(tekstikentta);
		vlay.addComponent(nappula);
		vlay.addComponent(label);
		
		// Tapahtuman k‰sittely nappulalle
		
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

			ProcessBuilder pb = new ProcessBuilder("python", "test.py");
			Process p = pb.start();
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String paluu = new String(in.readLine());
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
			String[] param = event.getParameters().split("/");
			tehtAnto.setValue(param[0]);
		}
		
	}
}
