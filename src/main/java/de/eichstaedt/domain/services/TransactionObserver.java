package de.eichstaedt.domain.services;

import java.util.HashMap;

import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.events.DomainEvent;

@Component
public class TransactionObserver implements Observer {

  private HashMap<String, Ausgabe> empfangendeAusgaben;

  public TransactionObserver() {
    super();

    this.empfangendeAusgaben = new HashMap<String, Ausgabe>();
  }

  public TransactionObserver(Observable subject) {
    subject.addObserver(this);
  }

  @Override
  public void processEvent(DomainEvent event) throws Exception {

    System.out.println("Getting Domain Event of Type and Data Object Class " + event.getDataTyp()
        + " " + event.getData().getClass());

    this.empfangendeAusgaben.put(Integer.toString(((Ausgabe) event.getData()).getId()),
        (Ausgabe) event.getData());
  }

  public HashMap<String, Ausgabe> getEmpfangendeAusgaben() {
    return empfangendeAusgaben;
  }

  public void setEmpfangendeAusgaben(HashMap<String, Ausgabe> empfangendeAusgaben) {
    this.empfangendeAusgaben = empfangendeAusgaben;
  }

}
