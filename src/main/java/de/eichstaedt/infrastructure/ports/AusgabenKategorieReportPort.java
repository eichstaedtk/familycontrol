package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;

import de.eichstaedt.domain.entities.AusgabenKategorieReport;
import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

public interface AusgabenKategorieReportPort extends CrudRepository<AusgabenKategorieReport, Integer> {

  public AusgabenKategorieReport findByKategorie(AusgabenKategorie kategorie);

}
