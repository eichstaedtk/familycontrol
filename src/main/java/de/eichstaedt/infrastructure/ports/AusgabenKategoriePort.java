package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;

import de.eichstaedt.domain.valueobjects.AusgabenKategorie;

public interface AusgabenKategoriePort extends CrudRepository<AusgabenKategorie, Integer> {

}
