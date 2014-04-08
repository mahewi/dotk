package com.example.ohjelmoinninalkeet;

import com.vaadin.server.VaadinRequest;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.themes.Runo;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

/**
 * Luokka m‰‰ritt‰‰ ponnahdusikkunan, joka aukeaa k‰ytt‰j‰n painaessa "Arvioi"-paniketta.
 * N‰kym‰ sis‰lt‰‰ tiedon vastauksen oikeellisuudesta.
 * 
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 */
public class PalautePopup extends Window {
	
    public PalautePopup(String oikeellisuus) {
        super("Teht‰v‰n palaute");
        center();
        
        Button sulje = new Button("Sulje");
        sulje.setStyleName(Runo.BUTTON_DEFAULT);
        sulje.setWidth("25%");
        Label palaute = new Label("<p class='teht'>" + oikeellisuus + "</p>");
        palaute.setStyleName("palauteStyle");
        palaute.setContentMode(ContentMode.HTML);
        
        setHeight("20%");
        setWidth("20%");
        
        VerticalLayout sisalto = new VerticalLayout();
        sisalto.setSizeFull();
        sisalto.addComponent(palaute);
        sisalto.addComponent(sulje);
        sisalto.setMargin(true);
        sisalto.setComponentAlignment(sulje, Alignment.MIDDLE_CENTER);
        sisalto.setComponentAlignment(palaute, Alignment.MIDDLE_CENTER);
        setContent(sisalto);
        
        setClosable(false);
        setResizable(false);
        
        // Tapahtuman k‰sittely sulje-painikkeelle. Napin painallus sulkee ikkunan.
        sulje.addClickListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                close();
            }
        });
    }
}