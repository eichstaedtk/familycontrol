package de.eichstaedt.infrastructure.ports;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.eichstaedt.domain.entities.Ausgabe;

public interface AusgabenPort extends PagingAndSortingRepository<Ausgabe, Integer>{
}
