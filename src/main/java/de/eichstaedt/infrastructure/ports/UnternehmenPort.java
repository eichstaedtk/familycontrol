package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.CrudRepository;

import de.eichstaedt.domain.entities.Unternehmen;

public interface UnternehmenPort extends CrudRepository<Unternehmen, Integer> {

}
