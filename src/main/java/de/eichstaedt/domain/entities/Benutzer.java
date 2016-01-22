package de.eichstaedt.domain.entities;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import de.eichstaedt.domain.valueobjects.Adresse;
import de.eichstaedt.domain.valueobjects.Name;

@Entity
@Table(name = "BENUTZER")
public class Benutzer {
	
	@Id
	@GeneratedValue
	private int id;
	
	@Embedded
	@AttributeOverride(name ="ADRESSE", column = @Column(name = "BENUTZER_ADRESSE"))
	private Adresse adresse;
	
	@Embedded
	@AttributeOverride(name ="NAME", column = @Column(name = "BENUTZER_NAME"))
	private Name name;
	
	protected Benutzer() {}

	public Benutzer(Name name,Adresse adresse) {
		super();
		this.adresse = adresse;
		this.name = name;
	}

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
