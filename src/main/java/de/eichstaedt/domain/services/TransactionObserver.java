package de.eichstaedt.domain.services;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.events.DomainEvent;

public class TransactionObserver {

  private HashMap<String, Ausgabe> empfangendeAusgaben;

  private Logger logger = LoggerFactory.getLogger(TransactionObserver.class);

  public void processEvent(DomainEvent event) throws Exception {

    logger.info("Getting Domain Event of Type {} and Data Object Class {} ", event.getDataTyp(),
        event.getData().getClass());

    if (this.empfangendeAusgaben == null)
      this.empfangendeAusgaben = new HashMap<String, Ausgabe>();

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
