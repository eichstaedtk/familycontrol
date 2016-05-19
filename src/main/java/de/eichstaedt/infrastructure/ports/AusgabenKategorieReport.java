package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;

import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

public interface AusgabenKategorieReport extends CrudRepository<AusgabenKategorieBericht, Integer> {

  public AusgabenKategorieBericht findByKategorie(AusgabenKategorie kategorie);

}
