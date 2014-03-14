package com.example.ohjelmoinninalkeet;

import java.sql.*;
import java.util.Random;
import java.util.ArrayList;

/**
 * Tietokanta luokka sis‰lt‰‰ tietokantaan liittyv‰t toiminnallisuudet.
 * Yhteydenoton ja tietojen hakemisen.
 * @author Marco Willgren & Tatu Sepp‰-Lassila
 *
 */
public class Tietokanta {
	
	private Connection yhteys;
	
	public Tietokanta() {
		this.yhteys = yhdistaKantaan();
	}
	
	/**
	 * Luo yhteyden sqlite-tietokantaan.
	 * @return Yhteys-olion
	 */
	public Connection yhdistaKantaan() {
		Connection c = yhteys;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:pythonTehtavat.db");
	    }
	    catch (Exception e) {
	    	System.out.println(e.toString());
	    }
	    return c;
	}
	
	/**
	 * Metodi hakee argumenttina saamansa mukaisen (satunnaisen) teht‰v‰n tietokannasta. 
	 * @param tyyppi
	 * @return Satunnaisesti valitun teht‰v‰n tiedot ArrayListin‰
	 */
	public ArrayList<String> annaTehtava(String tyyppi) {
		
		Random rand = new Random();
		Integer riviMaara = new Integer(0);
		ArrayList<String> tehtava = new ArrayList<String>();
		
		try {
			Statement st = yhteys.createStatement();
			ResultSet rs = st.executeQuery("select count(*) from '" + tyyppi + "'");
			riviMaara = Integer.parseInt(rs.getString(1));
			int random = rand.nextInt(riviMaara) + 1;
			rs = st.executeQuery("select * from '" + tyyppi + "' where id='" + random + "'");
			
			ResultSetMetaData metadata = rs.getMetaData();
			int sarakkeet = metadata.getColumnCount();
			for (int i=2; i<=sarakkeet; i++) {
				tehtava.add(rs.getString(i));
			}
			yhteys.close();
		}
		catch (Exception e) {
	    	System.out.println(e.toString());
	    }
		return tehtava;
	}
		
}
