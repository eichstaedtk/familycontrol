package de.eichstaedt.domain.services;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.infrastructure.ports.AusgabenKategorieReportPort;
import de.eichstaedt.infrastructure.ports.AusgabenPort;

@Component
public class AusgabenReportService implements ReportService {

  @Autowired
  private AusgabenKategorieReportPort reportRepository;

  @Autowired
  private AusgabenPort ausgabenRepository;

  private Logger logger = LoggerFactory.getLogger(AusgabenReportService.class);

  @Override
  public void createReport() {

    logger.info("Creating new Ausgaben Kategorie Report");

    reportRepository.deleteAll();

    ((List<Ausgabe>) ausgabenRepository.findAll()).stream().forEach((Ausgabe ausgabe) -> {

      AusgabenKategorieBericht reportAlreadyExist =
          reportRepository.findByKategorie(ausgabe.getKategorie());

      if (reportAlreadyExist == null) {

        AusgabenKategorieBericht report = AusgabenKategorieBericht.build(ausgabe.getAmount(),
            ausgabe.getKategorie(), LocalDateTime.now());

        reportRepository.save(report);
      } else {

        AusgabenKategorieBericht report =
            AusgabenKategorieBericht.build(ausgabe.getAmount() + reportAlreadyExist.getBetrag(),
                ausgabe.getKategorie(), LocalDateTime.now());

        reportRepository.delete(reportAlreadyExist);
        reportRepository.save(report);

      }

    });

  }

}
