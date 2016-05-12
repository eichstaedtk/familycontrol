package de.eichstaedt.domain.services;

import de.eichstaedt.domain.events.DomainEvent;

public interface Observer {

  public void processEvent(DomainEvent event) throws Exception;

}
