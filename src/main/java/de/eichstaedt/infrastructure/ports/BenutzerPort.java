package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;

import de.eichstaedt.domain.entities.Benutzer;

public interface BenutzerPort extends CrudRepository<Benutzer,Integer> {

}
