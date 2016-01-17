package de.eichstaedt.infrastructure.testdata;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.infrastructure.ports.AusgabenPort;

@Component
public class TestAusgaben implements InitializingBean{
	
	@Autowired
	private AusgabenPort ausgabenPort;
	
	public void create() {
		
		List<Ausgabe> testAusgaben = new ArrayList<Ausgabe>();
		
		Ausgabe a1 = new Ausgabe(LocalDateTime.now(), 10.00, "Erste Ausgabe im Project");
		Ausgabe a2 = new Ausgabe(LocalDateTime.now(), 35.00, "Zweite Ausgabe im Project");
		Ausgabe a3 = new Ausgabe(LocalDateTime.now(), 55.50, "Dritte Ausgabe im Project");
		
		testAusgaben.add(a1);
		
		testAusgaben.add(a2);	
		testAusgaben.add(a3);	
		ausgabenPort.save(testAusgaben);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		create();		
	}

}
