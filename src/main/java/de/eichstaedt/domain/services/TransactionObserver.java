package de.eichstaedt.domain.services;

import org.springframework.stereotype.Component;

import de.eichstaedt.domain.events.DomainEvent;

@Component
public class TransactionObserver implements Observer {

  public TransactionObserver() {
    super();
  }

  public TransactionObserver(Observable subject) {
    subject.addObserver(this);
  }

  @Override
  public void processEvent(DomainEvent event) throws Exception {

    System.out.println("Getting Domain Event of Type and Data Object Class " + event.getDataTyp()
        + " " + event.getData().getClass());

  }

}
