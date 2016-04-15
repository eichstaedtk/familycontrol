package de.eichstaedt.domain.valueobjects;

import javax.persistence.Embeddable;

/*
 * Immutable personal name value object class
 */

@Embeddable
public class Name {
	
	protected Name() {
		super();
		this.vorname = "";
		this.nachname = "";
		this.anrede = "";
		this.titel = "";
	};
	
	public Name(String vorname, String nachname, String anrede, String titel) {
		super();
		this.vorname = vorname;
		this.nachname = nachname;
		this.anrede = anrede;
		this.titel = titel;
	}

	private final String vorname;
	
	private final String nachname;
	
	private final String anrede;
	
	private final String titel;

	public String getVorname() {
		return vorname;
	}

	public String getNachname() {
		return nachname;
	}

	public String getAnrede() {
		return anrede;
	}

	public String getTitel() {
		return titel;
	}

	@Override
	public int hashCode() {
		return this.vorname.hashCode() + this.nachname.hashCode() + this.anrede.hashCode() + this.titel.hashCode();
	}
	
	@Override
	public String toString() {
		
		return this.vorname+" "+this.nachname;
	}
}
