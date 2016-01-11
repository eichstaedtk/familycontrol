package de.eichstaedt.domain.valueobjects;

public class Adresse {
	
	public String ort;
	
	public int postleitzahl;
	
	public String strasse;
	
	public String hausnummer;
	
	public String land;

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public int getPostleitzahl() {
		return postleitzahl;
	}

	public void setPostleitzahl(int postleitzahl) {
		this.postleitzahl = postleitzahl;
	}

	public String getStrasse() {
		return strasse;
	}

	public void setStrasse(String strasse) {
		this.strasse = strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public void setHausnummer(String hausnummer) {
		this.hausnummer = hausnummer;
	}

	public String getLand() {
		return land;
	}

	public void setLand(String land) {
		this.land = land;
	}

	
}
