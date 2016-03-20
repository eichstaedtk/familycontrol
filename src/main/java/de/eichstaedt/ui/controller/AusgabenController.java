package de.eichstaedt.ui.controller;

import java.time.ZoneOffset;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.extras.java8time.expression.Temporals;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.infrastructure.ports.AusgabenPort;

@Controller
@RequestMapping("/ausgaben")
public class AusgabenController {
	
	private final Temporals temporals = new Temporals(Locale.GERMAN, ZoneOffset.UTC);
	
	@Autowired
	private AusgabenPort ausgabenRepository;

	@RequestMapping("/ausgabenListe	")
	public String ausgabenListe() {
		
		return "ausgabenListe";
	}
	
	@ModelAttribute("allAusgaben")
	public List<Ausgabe> alleAusgaben() {
		
		return (List<Ausgabe>) ausgabenRepository.findAll();
	}
	
}
