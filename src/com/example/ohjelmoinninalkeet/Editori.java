package com.example.ohjelmoinninalkeet;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener.ViewChangeEvent;
import com.vaadin.server.ExternalResource;
import com.vaadin.ui.Link;
import com.vaadin.ui.Panel;
import com.vaadin.ui.VerticalLayout;

public class Editori extends Panel implements View {

	public static final String NAME = "editoriView";
	
	public Editori() {
		initLayout();
	}
	
	public void initLayout() {
		Link lnk = new Link("BÄK!:D", new ExternalResource("#!" + PythonUI.NAME));
		VerticalLayout vlay = new VerticalLayout();
		setContent(vlay);
		vlay.addComponent(lnk);
	}

	@Override
	public void enter(ViewChangeEvent event) {
		// TODO Auto-generated method stub
		
	}
}
