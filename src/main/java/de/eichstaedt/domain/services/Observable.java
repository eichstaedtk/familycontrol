package de.eichstaedt.domain.services;

import de.eichstaedt.domain.events.DomainEvent;

public interface Observable {

  public void addObserver(Observer observer);

  public void deleteObserver(Observer observer);

  public void informObserver(DomainEvent event);

}
