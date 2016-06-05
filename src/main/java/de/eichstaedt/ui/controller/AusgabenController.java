package de.eichstaedt.ui.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.eichstaedt.domain.entities.Ausgabe;
import de.eichstaedt.domain.entities.Benutzer;
import de.eichstaedt.domain.entities.Unternehmen;
import de.eichstaedt.domain.events.AusgabenActionEvent;
import de.eichstaedt.domain.events.DomainEvent;
import de.eichstaedt.domain.valueobjects.AusgabenKategorie;
import de.eichstaedt.infrastructure.ports.AusgabenKategoriePort;
import de.eichstaedt.infrastructure.ports.AusgabenPort;
import de.eichstaedt.infrastructure.ports.BenutzerPort;
import de.eichstaedt.infrastructure.ports.UnternehmenPort;

/**
 * Controller for Ausgabe Objects to control / manage all actions and data coming from view of
 * Ausgaben
 * 
 * @author konrad
 *
 */

@Component
@Controller
@RequestMapping("/ausgaben")
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AusgabenController implements InitializingBean {

  @Autowired
  private AusgabenPort ausgabenRepository;

  @Autowired
  private BenutzerPort benutzerRepository;

  @Autowired
  private UnternehmenPort unternehmenRepository;

  @Autowired
  private AusgabenKategoriePort ausgabenKategorienRepository;

  @Autowired
  private ApplicationEventPublisher publisher;

  private Logger logger = LoggerFactory.getLogger(AusgabenController.class);

  @RequestMapping("/ausgabenListe")
  public String ausgabenListe(
      @RequestParam(required = false, defaultValue = "date", name = "sortfield") String sortField,
      @RequestParam(required = false, defaultValue = "ASC",
          name = "sortdirection") String sortDirection,
      @RequestParam(required = false, defaultValue = "0", name = "page") String page,
      @RequestParam(required = false, defaultValue = "25", name = "pagesize") String pageSize,
      ModelMap model) {

    List<Ausgabe> result = new ArrayList<Ausgabe>();
    HashMap<String, Comparator<Ausgabe>> asc = new HashMap<String, Comparator<Ausgabe>>();
    HashMap<String, Comparator<Ausgabe>> desc = new HashMap<String, Comparator<Ausgabe>>();

    asc.put("id", (Ausgabe a1, Ausgabe a2) -> Integer.compare(a1.getId(), a2.getId()));
    asc.put("date", (Ausgabe a1, Ausgabe a2) -> a1.getDate().compareTo(a2.getDate()));
    asc.put("amount", (Ausgabe a1, Ausgabe a2) -> a1.getAmount().compareTo(a2.getAmount()));
    asc.put("description",
        (Ausgabe a1, Ausgabe a2) -> a1.getDescription().compareTo(a2.getDescription()));

    desc.put("id", (Ausgabe a1, Ausgabe a2) -> Integer.compare(a2.getId(), a1.getId()));
    desc.put("date", (Ausgabe a1, Ausgabe a2) -> a2.getDate().compareTo(a1.getDate()));
    desc.put("amount", (Ausgabe a1, Ausgabe a2) -> a2.getAmount().compareTo(a1.getAmount()));
    desc.put("description",
        (Ausgabe a1, Ausgabe a2) -> a2.getDescription().compareTo(a1.getDescription()));

    switch (sortDirection) {
      case "ASC":
        result = ((List<Ausgabe>) ausgabenRepository.findAll()).stream()
            .skip(Long.parseLong(page) * Long.parseLong(pageSize)).limit(Long.parseLong(pageSize))
            .sorted(asc.get(sortField) != null ? asc.get(sortField) : asc.get("id"))
            .collect(Collectors.toList());
        break;
      case "DESC":
        result = ((List<Ausgabe>) ausgabenRepository.findAll()).stream()
            .skip(Long.parseLong(page) * Long.parseLong(pageSize)).limit(Long.parseLong(pageSize))
            .sorted(desc.get(sortField) != null ? desc.get(sortField) : desc.get("id"))
            .collect(Collectors.toList());
        break;

      default:
        result = ((List<Ausgabe>) ausgabenRepository.findAll()).stream()
            .skip(Long.parseLong(page) * Long.parseLong(pageSize)).limit(Long.parseLong(pageSize))
            .sorted(asc.get(sortField)).collect(Collectors.toList());
        break;
    }


    model.addAttribute("alleAusgaben", result);


    return "ausgabenListe";
  }

  @RequestMapping(value = "/add", method = RequestMethod.GET)
  public String addAusgabe(ModelMap model) {

    model.addAttribute("ausgabe", new Ausgabe());

    return "addAusgabe";
  }

  @RequestMapping(value = "/add", method = RequestMethod.POST)
  public String addAusgabe(@Valid Ausgabe ausgabe, BindingResult bindingResult, Model model) {

    logger.info(
        "Trying to save the new Ausgabe {} description {}  date {} Unternehmen {} Betrag {} ",
        ausgabe.getId(), ausgabe.getDescription(), ausgabe.getDate(),
        ausgabe.getHaendler().getName(), ausgabe.getAmount());
    ausgabenRepository.save(ausgabe);

    AusgabenActionEvent event = new AusgabenActionEvent(ausgabe, DomainEvent.TYP.AUSGABE_CREATE);

    publisher.publishEvent(event);

    return "redirect:ausgabenListe";
  }

  @RequestMapping(value = "/delete", method = RequestMethod.POST)
  public String deleteAusgabe(@Valid Ausgabe ausgabe) {

    logger.info("Delete a ausgabe from repository {} ", ausgabe.getId());

    ausgabenRepository.delete(ausgabe);

    AusgabenActionEvent event = new AusgabenActionEvent(ausgabe, DomainEvent.TYP.AUSGABE_DELETE);

    publisher.publishEvent(event);

    return "redirect:ausgabenListe";

  }

  @ModelAttribute("alleUnternehmen")
  public List<Unternehmen> getAlleUnternehmen() {
    return (List<Unternehmen>) unternehmenRepository.findAll();
  }

  @ModelAttribute("alleBenutzer")
  public List<Benutzer> getAlleBenutzer() {
    return (List<Benutzer>) benutzerRepository.findAll();
  }

  @ModelAttribute("alleAusgabenKategorien")
  public List<AusgabenKategorie> getAllKategories() {
    return (List<AusgabenKategorie>) ausgabenKategorienRepository.findAll();
  }

  @Override
  public void afterPropertiesSet() throws Exception {

    logger.info("######################### CREATION OF AUSGABENCONTROLLER ##############");

  }
}
