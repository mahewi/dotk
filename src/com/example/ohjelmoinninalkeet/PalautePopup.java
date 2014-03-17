package com.example.ohjelmoinninalkeet;

import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class PalautePopup extends Window {
	
    public PalautePopup(String oikeellisuus) {
        super("Tehtävän palaute");
        center();
        
        Button sulje = new Button("Sulje");
        sulje.setStyleName("suljeStyle");
        Label palaute = new Label(oikeellisuus);
        palaute.setStyleName("palauteStyle");
        
        setHeight("20%");
        setWidth("20%");
        
        VerticalLayout sisalto = new VerticalLayout();
        sisalto.setSizeFull();
        sisalto.addComponent(palaute);
        sisalto.addComponent(sulje);
        sisalto.setMargin(true);
        sisalto.setComponentAlignment(sulje, Alignment.BOTTOM_CENTER);
        sisalto.setComponentAlignment(palaute, Alignment.MIDDLE_CENTER);
        setContent(sisalto);
        
        setClosable(false);
        setResizable(false);

        sulje.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
    }
}