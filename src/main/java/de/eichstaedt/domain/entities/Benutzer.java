package de.eichstaedt.domain.entities;

import de.eichstaedt.domain.valueobjects.Adresse;
import de.eichstaedt.domain.valueobjects.Name;

public class Benutzer {
	
	private int id;
	
	private Adresse adresse;
	
	private Name name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}
	
	

}
