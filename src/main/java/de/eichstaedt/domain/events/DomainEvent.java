package de.eichstaedt.domain.events;

public interface DomainEvent {

  public enum TYP {
    AUSGABE_CREATE, AUSGABE_DELETE, AUSGABE_UPDATE, LOGIN
  }

  public Object getData();

  public DomainEvent.TYP getDataTyp();

}
