package de.eichstaedt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("de.eichstaedt")
public class FamilyappApplication {

  private static Logger logger = LoggerFactory.getLogger(FamilyappApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FamilyappApplication.class, args);

    logger.info("FamilyControl Application started");
  }

}
