package com.example.ohjelmoinninalkeet;

//import com.sun.nio.sctp.Notification;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.ui.Alignment;
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
	TextArea label = new TextArea();
	Label tehtAnto = new Label("");

	public Editori() {
		initLayout();
	}
	
	public void initLayout() {
		
		tekstikentta.setWidth(50.0f, TextArea.Unit.PERCENTAGE);
		tekstikentta.setRows(30);
		tehtAnto.setStyleName("tehtavanAnto");
		
		vlay.addComponent(takaisin);
		vlay.addComponent(tehtAnto);
		vlay.addComponent(tekstikentta);
		vlay.addComponent(nappula);
		vlay.addComponent(label);
		vlay.setComponentAlignment(tekstikentta, Alignment.MIDDLE_CENTER);
		
		// Tapahtuman k‰sittely nappulalle
		nappula.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				label.setValue(toPython(tekstikentta.getValue()));
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
			tehtAnto.setValue(event.getParameters() + ":");
		}
		
	}
}
