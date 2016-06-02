package de.eichstaedt.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.domain.events.DomainEvent;
import de.eichstaedt.infrastructure.ports.AusgabenKategorieReport;
import de.eichstaedt.infrastructure.ports.AusgabenPort;

@Component
@Scope("request")
public class AusgabenReportHandler implements DomainEventHandler {

  @Autowired
  private AusgabenKategorieReport reportRepository;
  
  private AusgabenPort ausgabenRepository;

  @Override
  public void handle(DomainEvent event) {
    Ausgabe ausgabe = (Ausgabe) event.getData();
    
    ((List<Ausgabe>)ausgabenRepository.findAll()).stream().forEach((Ausgabe ausgabe1) -> {
    	
    });

    if (((List<AusgabenKategorieBericht>) reportRepository.findAll()).stream()
        .noneMatch(report -> report.getKategorie().getId() == (ausgabe.getKategorie().getId()))
        && event.getDataTyp().equals(DomainEvent.TYP.AUSGABE_CREATE)) {

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
