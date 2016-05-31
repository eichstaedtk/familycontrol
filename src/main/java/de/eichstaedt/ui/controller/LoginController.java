package de.eichstaedt.ui.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.eichstaedt.domain.valueobjects.Authentication;

@Controller
public class LoginController {

  private Authentication authentification;

  private Logger logger = LoggerFactory.getLogger(LoginController.class);

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {

    authentification = Authentication.build("", "");

    model.addAttribute("auth", authentification);

    logger.info("Getting authentification object with login {} and password {}",
        authentification.getLoginName(), authentification.getPassword());

    return "login";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login(@Valid Authentication auth, BindingResult bindingResult, Model model) {

    logger.info("Login POST with auth login {} and password {} ", auth.getLoginName(),
        auth.getPassword());

    return "redirect:ausgaben/kategorieReport";
  }

  public Authentication getAuthentification() {
    return authentification;
  }

  public void setAuthentification(Authentication authentification) {
    this.authentification = authentification;
  }

}
