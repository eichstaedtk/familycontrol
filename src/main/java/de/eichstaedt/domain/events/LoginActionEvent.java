package de.eichstaedt.domain.events;

import de.eichstaedt.domain.entities.Benutzer;

public class LoginActionEvent implements DomainEvent {

  private DomainEvent.TYP typ;

  private Benutzer nutzer;

  public LoginActionEvent(Benutzer nutzer, DomainEvent.TYP typ) {
    this.nutzer = nutzer;
    this.typ = typ;
  }

  @Override
  public Object getData() {

    return nutzer;
  }

  @Override
  public TYP getDataTyp() {

    return typ;
  }

}
