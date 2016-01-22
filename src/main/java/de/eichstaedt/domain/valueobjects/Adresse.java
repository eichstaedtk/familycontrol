package de.eichstaedt.domain.valueobjects;

import javax.persistence.Embeddable;

/*
 * Immutable address value object class
 */

@Embeddable
public class Adresse {
	
	private final String ort;
	
	private final int postleitzahl;
	
	private final String strasse;
	
	private final String hausnummer;
	
	private final String land;
	
	protected Adresse() {
		this.strasse = "";
		this.postleitzahl = 0;
		this.ort = "";
		this.hausnummer = "";
		this.land = "";
	}
	
	public Adresse(String ort, int plz, String strasse, String hausnummer, String land)
	{
		super();
		this.ort = ort;
		this.postleitzahl = plz;
		this.strasse = strasse;
		this.hausnummer = hausnummer;
		this.land = land;
	}

	public String getOrt() {
		return ort;
	}

	public int getPostleitzahl() {
		return postleitzahl;
	}

	public String getStrasse() {
		return strasse;
	}

	public String getHausnummer() {
		return hausnummer;
	}

	public String getLand() {
		return land;
	}
	
	public String toString() {
		return this.ort+" "+this.postleitzahl+" "+this.strasse+" "+this.hausnummer+" , "+this.land;
	}

	@Override
	public int hashCode() {
		return this.ort.hashCode() + this.postleitzahl + this.strasse.hashCode() + this.hausnummer.hashCode()+ this.land.hashCode();
	}
	
}
