package de.eichstaedt.ui.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Produces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.infrastructure.ports.AusgabenPort;

@RestController
@RequestMapping("/rest/ausgaben/")
public class AusgabenRESTController {

	@Autowired
	private AusgabenPort ausgabenPort;
	
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	@Produces(value="application/json")
	public ResponseEntity<?> getAll(HttpServletRequest request, HttpServletResponse response) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return new ResponseEntity<List<Ausgabe>>((List<Ausgabe>) ausgabenPort.findAll(),headers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Produces(value="application/json")
	public ResponseEntity<?> getAusgabe(@PathVariable("id") int id) {
		
		Ausgabe ausgabe = ausgabenPort.findOne(id);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setETag("\""+Integer.toString(ausgabe.getDate().hashCode())+"\"");
		
		return new ResponseEntity<Ausgabe>(ausgabe,headers, HttpStatus.OK);

	}
	
	
}
