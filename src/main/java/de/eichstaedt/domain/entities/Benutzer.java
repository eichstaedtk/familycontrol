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
import de.eichstaedt.domain.valueobjects.Authentication;
import de.eichstaedt.domain.valueobjects.Name;

@Entity
@Table(name = "BENUTZER")
public class Benutzer {

  @Id
  @GeneratedValue
  private int id;

  @Embedded
  @AttributeOverride(name = "ADRESSE", column = @Column(name = "BENUTZER_ADRESSE") )
  private Adresse adresse;

  @Embedded
  @AttributeOverride(name = "NAME", column = @Column(name = "BENUTZER_NAME") )
  private Name name;

  @Embedded
  @AttributeOverride(name = "AUTH", column = @Column(name = "BENUTZER_AUTH") )
  private Authentication authentification;

  protected Benutzer() {}

  public Benutzer(Name name, Adresse adresse, Authentication authentification) {
    super();
    this.adresse = adresse;
    this.name = name;
    this.authentification = authentification;
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

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
  
  @Override
  public boolean equals(Object obj) {
      
	  if(!(obj instanceof Benutzer))
      {
    	  	return false;
      }
	  
	  return Objects.equals(this.getId(), ((Benutzer)obj).getId());
			  
  }

  public static Benutzer build() {
    return new Benutzer(new Name("", "", "", ""), new Adresse("", 0, "", "", ""),
        Authentication.build("", ""));
  }

}
