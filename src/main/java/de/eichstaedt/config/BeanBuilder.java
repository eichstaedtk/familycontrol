package de.eichstaedt.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import de.eichstaedt.domain.services.ReportObserver;
import de.eichstaedt.domain.services.TransactionObserver;
import de.eichstaedt.ui.controller.AusgabenController;

/**
 * Class which build all DI Beans for the Application
 * 
 * 
 * @author konrad
 *
 */

@Component
public class BeanBuilder {

  @Autowired
  private AusgabenController controller;

  private TransactionObserver transactionObserver;

  private ReportObserver reportObserver;

  private Logger logger = LoggerFactory.getLogger(BeanBuilder.class);

  @Bean
  public Jackson2ObjectMapperBuilder jacksonBuilder() {
    Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
    builder.indentOutput(true).simpleDateFormat("yyyy-MM-dd");
    return builder;
  }

  @Bean
  public Java8TimeDialect java8TimeDialect() {
    return new Java8TimeDialect();
  }

  @Bean
  public TransactionObserver getTransactionObserver() {

    if (this.transactionObserver == null) {
      logger.info("Erzeuge neuen TransactionObserver!");
      return new TransactionObserver(controller);
    } else {
      logger.info("Gebe bestehende Instanz Referenz zurück");
      return this.transactionObserver;
    }

  }

  @Bean
  public ReportObserver getReportObserver() {

    if (this.reportObserver == null) {
      return new ReportObserver(controller);
    } else {
      return this.reportObserver;
    }

  }

}
