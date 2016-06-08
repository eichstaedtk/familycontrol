package de.eichstaedt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 * Class which build all DI Beans for the Application
 * 
 * 
 * @author konrad
 *
 */

@Component
public class BeanBuilder {


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
