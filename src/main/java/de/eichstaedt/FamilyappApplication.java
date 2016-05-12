package de.eichstaedt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import de.eichstaedt.domain.services.TransactionObserver;
import de.eichstaedt.ui.controller.AusgabenController;

@SpringBootApplication
@EnableJpaRepositories("de.eichstaedt")
public class FamilyappApplication {

  @Autowired
  private AusgabenController controller;

  private static Logger logger = LoggerFactory.getLogger(FamilyappApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FamilyappApplication.class, args);

    logger.info("FamilyControl Application started");
  }

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

    return new TransactionObserver(controller);

  }
}
