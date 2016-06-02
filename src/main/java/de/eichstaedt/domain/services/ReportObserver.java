package de.eichstaedt.domain.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import de.eichstaedt.domain.events.DomainEvent;

/**
 * Class that should generate the different statistics reports.
 * 
 * @author konrad
 *
 */

public class ReportObserver implements Observer, InitializingBean {

  private Logger logger = LoggerFactory.getLogger(ReportObserver.class);

  @Autowired
  private AusgabenReportHandler reportHandler;

  public ReportObserver(Observable observable) {
    observable.addObserver(this);
  }

  @Override
  public void processEvent(DomainEvent event) throws Exception {

    logger.info("Getting Domain Event of typ {} and data class {}", event.getDataTyp(),
        event.getData().getClass());

    reportHandler.handle(event);

  }

  @Override
  public void afterPropertiesSet() throws Exception {
    logger.info("################# CREATION OF REPORTOBSERVER ##############");

  }

}
