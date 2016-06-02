package de.eichstaedt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@SpringBootApplication
@EnableJpaRepositories("de.eichstaedt")
public class FamilyappApplication {

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
}
