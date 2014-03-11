package com.example.ohjelmoinninalkeet;

public class Tehtava {
	private String tehtAnto = "";
	private String tuloste = "";
	private String oikeaVastaus = "";
	
	public String annaTehtAnto() {
		return this.tehtAnto;
	}
	
	/**
	 * @return the tuloste
	 */
	public String getTuloste() {
		return tuloste;
	}

	/**
	 * @return the oikeaVastaus
	 */
	public String getOikeaVastaus() {
		return oikeaVastaus;
	}
}