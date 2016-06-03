package de.eichstaedt.ui.controller;

import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import de.eichstaedt.domain.entities.AusgabenKategorieBericht;
import de.eichstaedt.infrastructure.ports.AusgabenKategorieReportPort;

@Component
@Controller
@RequestMapping("/ausgaben")
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AusgabenKategorieReportController {
	
	@Autowired
	private AusgabenKategorieReportPort reportRepository;
	
	private int counter;
	
	private Logger logger = LoggerFactory.getLogger(AusgabenKategorieReportController.class);
	
	@RequestMapping("kategorieReport")
	public String getAusgabenKategorieReport(ModelMap model) {
		
		model.addAttribute("reports", (List<AusgabenKategorieBericht>)reportRepository.findAll());
		counter = 1;
		
		logger.info("Generate the report with counter {} and all reports {} ",counter,((List<AusgabenKategorieBericht>)reportRepository.findAll()).size());
		
		Object [][] reportArray = new Object[((List<AusgabenKategorieBericht>)reportRepository.findAll()).size()+1][2];
		reportArray[0][0] = "Ausgabe";
		reportArray[0][1] = "Kategorie";
		
		((List<AusgabenKategorieBericht>)reportRepository.findAll()).stream().forEach((bericht)->{
			logger.info("Put report array {}",reportArray.toString());
			reportArray[counter][0] = bericht.getKategorie().getName();
			reportArray[counter][1] = Math.round(bericht.getBetrag());
			counter++;
		});
		
		
		logger.info("Report Array {}", Arrays.toString(reportArray));
		
		
		model.addAttribute("reportArray", reportArray);
		
		return "ausgabenKategorieReport";
	}
	
}
