package de.eichstaedt.ui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.infrastructure.ports.AusgabenKategorieReport;

@Component
@Controller
@RequestMapping("/ausgaben")
public class AusgabenKategorieReportController {
	
	@Autowired
	private AusgabenKategorieReport reportRepository;
	
	@RequestMapping("kategorieReport")
	public String getAusgabenKategorieReport(ModelMap model) {
		
		model.addAttribute("reports", (List<AusgabenKategorieBericht>)reportRepository.findAll());
		
		return "ausgabenKategorieReport";
	}
	
}
