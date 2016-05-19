package de.eichstaedt.domain.valueobjects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Class which represent a category of ausgaben entity. Important to group Ausgaben within a report.
 */

@Entity
public class AusgabenKategorie {

  @Id
  @GeneratedValue
  private int id;

  private String name;

  private String beschreibung;

  public AusgabenKategorie() {}

  public static AusgabenKategorie build(String name, String beschreibung) {
    AusgabenKategorie kat = new AusgabenKategorie();
    kat.beschreibung = beschreibung;
    kat.name = name;

    return kat;
  }

  public String getName() {
    return name;
  }

  public String getBeschreibung() {
    return beschreibung;
  }

  public int getId() {
    return id;
  }

}
