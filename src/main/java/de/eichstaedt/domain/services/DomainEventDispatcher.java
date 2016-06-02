package de.eichstaedt.domain.services;

import java.util.List;

import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.events.DomainEvent;

@Component
public class DomainEventDispatcher {

  private List<Object> knownTargets;

  private DomainEventHandler handler;

  public void dispatch(DomainEvent event, Object target) {

    if (event.getData() instanceof Ausgabe) {

      handler = new AusgabenReportHandler();

    }

    if (handler != null)
      handler.handle(event);
  }

}
