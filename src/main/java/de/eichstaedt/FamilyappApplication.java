package de.eichstaedt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import de.eichstaedt.domain.services.ReportObserver;
import de.eichstaedt.domain.services.TransactionObserver;
import de.eichstaedt.ui.controller.AusgabenController;

@SpringBootApplication
@EnableJpaRepositories("de.eichstaedt")
@Configuration
@EnableWebSecurity
public class FamilyappApplication extends WebSecurityConfigurerAdapter{

  @Autowired
  private AusgabenController controller;

  private TransactionObserver transactionObserver;

  private ReportObserver reportObserver;

  private static Logger logger = LoggerFactory.getLogger(FamilyappApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(FamilyappApplication.class, args);

    logger.info("FamilyControl Application started");
  }
  
  protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll().and()
            .logout().logoutUrl("/logout");
		
	}
  
  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      auth
          .inMemoryAuthentication()
              .withUser("konrad").password("Start123").roles("USER");
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

    if (this.transactionObserver == null) {
      System.out.println("Erzeuge neuen TransactionObserver!");
      return new TransactionObserver(controller);
    } else {
      System.out.println("Gebe bestehende Instanz Referenz zurück");
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
