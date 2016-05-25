package de.eichstaedt.infrastructure.testdata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.Benutzer;
import de.eichstaedt.domain.entities.Unternehmen;
import de.eichstaedt.domain.valueobjects.Adresse;
import de.eichstaedt.domain.valueobjects.AusgabenKategorie;
import de.eichstaedt.domain.valueobjects.Authentication;
import de.eichstaedt.domain.valueobjects.Name;
import de.eichstaedt.infrastructure.ports.AusgabenKategoriePort;
import de.eichstaedt.infrastructure.ports.AusgabenPort;
import de.eichstaedt.infrastructure.ports.BenutzerPort;
import de.eichstaedt.infrastructure.ports.UnternehmenPort;

@Component
public class TestAusgaben implements InitializingBean {

  @Autowired
  private AusgabenPort ausgabenPort;

  @Autowired
  private UnternehmenPort unternehmenRepository;

  @Autowired
  private BenutzerPort benutzerRepository;

  @Autowired
  private AusgabenKategoriePort kategorieRepository;

  public void create() {

    Adresse ksued = new Adresse("Rathenow", 14712, "Genthiner Strasse", "3a", "Deutschland");

    Adresse obiwest = new Adresse("Rathenow", 14712, "Genthiner Strasse", "12", "Deutschland");

    Benutzer konrad = new Benutzer(new Name("Konrad", "Eichstädt", "Herr", "Diplom Ingenieur"),
        new Adresse("Rathenow", 14712, "Göttliner Dorfstrasse", "12a", "Deutschland"),
        Authentication.build("konrad", "Start123"));

    Benutzer nicole =
        new Benutzer(new Name("Nicole", "Eichstädt", "Frau", "Diplom Verwaltungswirt"),
            new Adresse("Rathenow", 14712, "Göttliner Dorfstrasse", "12a", "Deutschland"),
            Authentication.build("nicole", "Start123"));

    benutzerRepository.save(konrad);
    benutzerRepository.save(nicole);

    Unternehmen kaufland = new Unternehmen("Kaufland Rathenow Süd", ksued);
    Unternehmen obi = new Unternehmen("Obi Rathenow West", obiwest);

    unternehmenRepository.save(obi);
    unternehmenRepository.save(kaufland);

    AusgabenKategorie katLebensmittel =
        AusgabenKategorie.build("Lebensmittel", "Alle Ausgaben zum Lebensunterhalt");

    AusgabenKategorie katHaus =
        AusgabenKategorie.build("Haus", "Instandhaltungskosten Haus und Hof");

    AusgabenKategorie katSport =
        AusgabenKategorie.build("Sport", "Vereins- und Kleidungskosten Sport");

    kategorieRepository.save(katHaus);
    kategorieRepository.save(katLebensmittel);
    kategorieRepository.save(katSport);

    List<Ausgabe> testAusgaben = new ArrayList<Ausgabe>();

    Ausgabe a1 = new Ausgabe(LocalDateTime.now(), 10.00, "Erste Ausgabe im Project", kaufland,
        konrad, katLebensmittel);
    Ausgabe a2 =
        new Ausgabe(LocalDateTime.now(), 35.00, "Zweite Ausgabe im Project", obi, konrad, katHaus);
    Ausgabe a3 = new Ausgabe(LocalDateTime.now(), 55.50, "Dritte Ausgabe im Project", kaufland,
        konrad, katLebensmittel);

    testAusgaben.add(a1);

    testAusgaben.add(a2);
    testAusgaben.add(a3);
    ausgabenPort.save(testAusgaben);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    create();
  }

}
