package de.eichstaedt.domain.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

/**
 * The core entity which should also be the main aggregate.
 */

@Entity
@Table(name = "ausgaben")
public class Ausgabe {

  @GeneratedValue
  @Id
  private int id;

  @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm:ss")
  private LocalDateTime date;

  private Double amount;

  private String description;

  @OneToOne
  @JoinColumn(name = "AUSG_BENUTZER")
  private Benutzer benutzer;

  @OneToOne
  @JoinColumn(name = "AUSG_UNTERNEHMEN")
  private Unternehmen haendler;

  @OneToOne
  @JoinColumn(name = "AUSG_KATEGORIE")
  private AusgabenKategorie kategorie;

  public Ausgabe() {}

  public Ausgabe(LocalDateTime date, Double amount, String description, Unternehmen haendler,
      Benutzer benutzer, AusgabenKategorie kategorie) {
    this.date = date;
    this.amount = amount;
    this.description = description;
    this.haendler = haendler;
    this.benutzer = benutzer;
    this.kategorie = kategorie;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDateTime getDate() {
    return date;
  }

  public void setDate(LocalDateTime date) {
    this.date = date;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Unternehmen getHaendler() {
    return haendler;
  }

  public void setHaendler(Unternehmen haendler) {
    this.haendler = haendler;
  }

  public Benutzer getBenutzer() {
    return benutzer;
  }

  public void setBenutzer(Benutzer benutzer) {
    this.benutzer = benutzer;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getId());
  }
  
  @Override
  public boolean equals(Object obj) {
      
	  if(!(obj instanceof Ausgabe))
      {
    	  	return false;
      }
	  
	  return Objects.equals(this.getId(), ((Ausgabe)obj).getId());
			  
  }

  public AusgabenKategorie getKategorie() {
    return kategorie;
  }

  public void setKategorie(AusgabenKategorie kategorie) {
    this.kategorie = kategorie;
  }
}
