package de.eichstaedt.domain.events;

import de.eichstaedt.domain.entities.Ausgabe;

public class AusgabenActionEvent implements DomainEvent {

  private Ausgabe ausgabe;

  private DomainEvent.TYP typ;

  public AusgabenActionEvent(Ausgabe ausgabe, DomainEvent.TYP typ) {
    this.ausgabe = ausgabe;
    this.typ = typ;
  }

  @Override
  public Object getData() {

    return ausgabe;
  }

  @Override
  public TYP getDataTyp() {

    return typ;
  }



}
