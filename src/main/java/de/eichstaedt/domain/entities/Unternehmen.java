package de.eichstaedt.domain.entities;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import de.eichstaedt.domain.valueobjects.Adresse;

@Entity
@Table(name = "haendler")
public class Unternehmen {
	
	@Id
	@GeneratedValue
	private int id;
	
	private String name;
	
	@Embedded
	@AttributeOverride(name ="adresse", column = @Column(name = "handler_adresse"))
	private Adresse adresse;
	
	protected Unternehmen() {}
	
	public Unternehmen(String name, Adresse adresse)
	{
		this.name = name;
		this.adresse = adresse;
	}

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
	
	@Override
	  public int hashCode() {
	    return Objects.hash(this.getId());
	  }
	  
	  @Override
	  public boolean equals(Object obj) {
	      
		  if(!(obj instanceof Unternehmen))
	      {
	    	  	return false;
	      }
		  
		  return Objects.equals(this.getId(), ((Unternehmen)obj).getId());
				  
	  }

}
