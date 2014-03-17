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
        
        String teksti = "";
        tutoriaali.setValue(teksti);
        sulje.setStyleName("suljeStyle");
        
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
