package de.eichstaedt.domain.service;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import de.eichstaedt.FamilyappApplication;
import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.Benutzer;
import de.eichstaedt.domain.entities.Unternehmen;
import de.eichstaedt.domain.services.TransactionObserver;
import de.eichstaedt.domain.valueobjects.Adresse;
import de.eichstaedt.domain.valueobjects.Name;
import de.eichstaedt.ui.controller.AusgabenController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FamilyappApplication.class)
@WebAppConfiguration
public class TransactionObserverTest {

  @Autowired
  private AusgabenController controller;

  @Autowired
  private BindingResult binding;

  @Autowired
  private TransactionObserver transactionObserver;

  @Autowired
  private Model model;

  @Test
  public void testObserver() {

    Ausgabe ausgabe = new Ausgabe(LocalDateTime.now(), 10.00, "Testausgabe",
        new Unternehmen("Test Kaufland",
            new Adresse("Rathenow", 14712, "Genthiner", "1a", "Deutschland")),
        new Benutzer(new Name("Konrad", "Eichstädt", "Herr", ""),
            new Adresse("Rathenow", 14712, "Göttliner Dorfstraße", "1a", "Deutschland")));

    controller.addAusgabe(ausgabe, binding, model);

    org.junit.Assert.assertTrue(transactionObserver.getEmpfangendeAusgaben().size() == 1);

  }

}
