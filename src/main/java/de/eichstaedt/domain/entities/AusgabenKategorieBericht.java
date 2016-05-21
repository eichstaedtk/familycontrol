package de.eichstaedt.domain.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

@Entity
public class AusgabenKategorieBericht {

  @Id
  @GeneratedValue
  private int id;

  private double betrag;

  @OneToOne
  @JoinColumn(name = "AUSG_KATEGORIE")
  private AusgabenKategorie kategorie;

  private LocalDateTime lastModified;

  protected AusgabenKategorieBericht() {
    super();
  }

  private AusgabenKategorieBericht(double betrag, AusgabenKategorie kategorie,
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

  public static AusgabenKategorieBericht build(double betrag, AusgabenKategorie kategorie,
      LocalDateTime date) {
    AusgabenKategorieBericht report = new AusgabenKategorieBericht(betrag, kategorie, date);
    return report;
  }

}
