package de.eichstaedt.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.domain.events.DomainEvent;
import de.eichstaedt.infrastructure.ports.AusgabenKategorieReport;

/**
 * Class that should generate the different statistics reports.
 * 
 * @author konrad
 *
 */

public class ReportObserver implements Observer {

  private Logger logger = LoggerFactory.getLogger(ReportObserver.class);

  @Autowired
  private AusgabenKategorieReport reportRepository;

  public ReportObserver(Observable observable) {
    observable.addObserver(this);
  }

  @Override
  public void processEvent(DomainEvent event) throws Exception {

    logger.info("Getting Domain Event of typ {} and data class {}", event.getDataTyp(),
        event.getData().getClass());

    Ausgabe ausgabe = (Ausgabe) event.getData();

    if (((List<AusgabenKategorieBericht>) reportRepository.findAll()).stream()
        .noneMatch(report -> report.getKategorie().getId() == (ausgabe.getKategorie().getId()))) {

      AusgabenKategorieBericht report = AusgabenKategorieBericht.build(ausgabe.getAmount(),
          ausgabe.getKategorie(), LocalDateTime.now());

      reportRepository.save(report);

    } else {

      AusgabenKategorieBericht report = reportRepository.findByKategorie(ausgabe.getKategorie());

      AusgabenKategorieBericht newReport = AusgabenKategorieBericht.build(
          ausgabe.getAmount() + report.getBetrag(), ausgabe.getKategorie(), LocalDateTime.now());

      reportRepository.delete(report);
      reportRepository.save(newReport);
    }



  }

}
