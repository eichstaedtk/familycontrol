package de.eichstaedt.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

@Entity
public class AusgabenKategorieReport {

  @Id
  @GeneratedValue
  private int id;

  private double betrag;

  @OneToOne
  @JoinColumn(name = "AUSG_KATEGORIE")
  private AusgabenKategorie kategorie;

  private LocalDateTime lastModified;

  protected AusgabenKategorieReport() {
    super();
  }

  private AusgabenKategorieReport(double betrag, AusgabenKategorie kategorie,
      LocalDateTime lastModified) {
    this.betrag = betrag;
    this.kategorie = kategorie;
    this.lastModified = lastModified;
  }

  public int getId() {
	return id;
}

public double getBetrag() {
    return betrag;
  }

  public AusgabenKategorie getKategorie() {
    return kategorie;
  }

  public LocalDateTime getLastModified() {
    return lastModified;
  }

  public static AusgabenKategorieReport build(double betrag, AusgabenKategorie kategorie,
      LocalDateTime date) {
    AusgabenKategorieReport report = new AusgabenKategorieReport(betrag, kategorie, date);
    return report;
  }
  
  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
  
  @Override
  public boolean equals(Object obj) {
      
	  if(!(obj instanceof AusgabenKategorieReport))
      {
    	  	return false;
      }
	  
	  return Objects.equals(this.getId(), ((AusgabenKategorieReport)obj).getId());
			  
  }

}
