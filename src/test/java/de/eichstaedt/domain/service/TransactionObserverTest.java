package de.eichstaedt.domain.service;

import java.time.LocalDateTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import de.eichstaedt.FamilyappApplication;
import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.Benutzer;
import de.eichstaedt.domain.entities.Unternehmen;
import de.eichstaedt.domain.services.TransactionObserver;
import de.eichstaedt.domain.valueobjects.Adresse;
import de.eichstaedt.domain.valueobjects.AusgabenKategorie;
import de.eichstaedt.domain.valueobjects.Authentication;
import de.eichstaedt.domain.valueobjects.Name;
import de.eichstaedt.infrastructure.ports.AusgabenKategoriePort;
import de.eichstaedt.infrastructure.ports.BenutzerPort;
import de.eichstaedt.infrastructure.ports.UnternehmenPort;
import de.eichstaedt.ui.controller.AusgabenController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = FamilyappApplication.class)
@WebAppConfiguration
public class TransactionObserverTest {

  @Autowired
  private AusgabenController controller;

  @Autowired
  private BenutzerPort benutzerRepository;

  @Autowired
  private UnternehmenPort unternehmenRepository;

  @Autowired
  private TransactionObserver transactionObserver;

  @Autowired
  private AusgabenKategoriePort ausgabenKategorieRepository;

  @Test
  public void testObserver() {

    Benutzer testNutzer = new Benutzer(new Name("Konrad", "Eichstädt", "Herr", ""),
        new Adresse("Rathenow", 14712, "Genthiner", "1a", "Deutschland"),
        Authentication.build("konrad", "Start123"));
    benutzerRepository.save(testNutzer);

    Unternehmen testUnternehmen = new Unternehmen("Test Kaufland",
        new Adresse("Rathenow", 14712, "Göttliner Dorfstraße", "1a", "Deutschland"));

    unternehmenRepository.save(testUnternehmen);

    AusgabenKategorie katLebensmittel =
        AusgabenKategorie.build("Lebensmittel", "Alle Ausgaben für Lebensmittel und Kosmetik");

    ausgabenKategorieRepository.save(katLebensmittel);

    Ausgabe ausgabe = new Ausgabe(LocalDateTime.now(), 10.00, "Testausgabe", testUnternehmen,
        testNutzer, katLebensmittel);


    controller.addAusgabe(ausgabe, null, null);

    Assert.assertTrue(transactionObserver.getEmpfangendeAusgaben().size() == 1);
  }

}
