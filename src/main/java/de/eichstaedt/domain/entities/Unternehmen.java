package de.eichstaedt.domain.entities;

import de.eichstaedt.domain.valueobjects.Adresse;

public class Unternehmen {
	
	private int id;
	
	private String name;
	
	private Adresse adresse;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Adresse getAdresse() {
		return adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
	}
	
	

}
