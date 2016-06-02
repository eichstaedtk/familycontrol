package de.eichstaedt.domain.services;

import de.eichstaedt.domain.events.DomainEvent;

public interface DomainEventHandler {

  public void handle(DomainEvent event);

}
