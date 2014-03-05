package com.example.ohjelmoinninalkeet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Label;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("ohjelmoinninalkeet")
public class OhjelmoinninalkeetUI extends UI {
	
	private Label otsikko = new Label("<h1>Ohjelmoinnin alkeet</h1>");
	private Label esittely = new Label("");
	private Label kielet = new Label("<h2>Valitse ohjelmointikieli</h2>");
	private Button python = new Button("Python");

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = OhjelmoinninalkeetUI.class)
	public static class Servlet extends VaadinServlet {
	}

	@Override
	protected void init(VaadinRequest request) {
		initLayout();
	}
	
	public void initLayout() {
		otsikko.setContentMode(ContentMode.HTML);
		esittely.setContentMode(ContentMode.HTML);
		kielet.setContentMode(ContentMode.HTML);
		
		String teksti = "<p>T‰ss‰ ohjelmassa on ohjelmoimisen alkeisiin liittyvi‰ teht‰vi‰. T‰h‰n tulee viel‰ lis‰‰ teksti‰...</p>";
		esittely.setValue(teksti);
		//python.setWidth("margin: 1ex");
		
		HorizontalSplitPanel split = new HorizontalSplitPanel();
		setContent(split);
		
		VerticalLayout vasenLayout = new VerticalLayout();
		VerticalLayout oikeaLayout = new VerticalLayout();
		
		split.addComponent(vasenLayout);
		split.addComponent(oikeaLayout);
		split.setLocked(true);
		split.setSplitPosition(60);
		
		vasenLayout.addComponent(otsikko);
		vasenLayout.addComponent(esittely);
		oikeaLayout.addComponent(kielet);
		oikeaLayout.addComponent(python);
		
		oikeaLayout.setComponentAlignment(python, Alignment.MIDDLE_CENTER);
	}

}