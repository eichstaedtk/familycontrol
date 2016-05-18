package de.eichstaedt.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.eichstaedt.domain.events.DomainEvent;

/**
 * Class that should generate the different statistics reports.
 * 
 * @author konrad
 *
 */

public class ReportObserver implements Observer {

  private Logger logger = LoggerFactory.getLogger(ReportObserver.class);

  public ReportObserver(Observable observable) {
    observable.addObserver(this);
  }

  @Override
  public void processEvent(DomainEvent event) throws Exception {

    logger.info("Getting Domain Event of typ {} and data class {}", event.getDataTyp(),
        event.getData().getClass());

  }

}
