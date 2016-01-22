package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;
import de.eichstaedt.domain.entities.Ausgabe;

public interface AusgabenPort extends CrudRepository<Ausgabe, Integer>{
}
