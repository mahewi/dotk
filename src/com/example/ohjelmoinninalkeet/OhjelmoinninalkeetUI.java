package com.example.ohjelmoinninalkeet;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.UI;

@SuppressWarnings("serial")
@Theme("ohjelmoinninalkeet")
/**
 * Luokassa alustetaan navigaattori-olio, jonka avulla ohjelmassa siirrytään eri näkymien välillä.
 * Navigaattoriolioon rekisteröidään kaikki halutut näkymät.
 * @author Marco Willgren & Tatu Seppä-Lassila
 *
 */
public class OhjelmoinninalkeetUI extends UI {

	@WebServlet(value = "/*", asyncSupported = true)
	@VaadinServletConfiguration(productionMode = false, ui = OhjelmoinninalkeetUI.class)
	public static class Servlet extends VaadinServlet {
	}
	
    @Override
    public void init(VaadinRequest request) {
    	
        // Luodaan Navigaattori-olio 
        Navigator navigator = new Navigator(this, this);
        
        // Näkymien rekisteröinti
        navigator.addView(AloitusView.NAME, new AloitusView());
        navigator.addView(PythonUI.NAME, new PythonUI());
        navigator.addView(Editori.NAME, new Editori());

    }
}

