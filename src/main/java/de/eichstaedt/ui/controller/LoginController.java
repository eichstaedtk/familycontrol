package de.eichstaedt.ui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.eichstaedt.domain.valueobjects.Authentication;

@Controller
public class LoginController {

  private Authentication authentification;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {

    model.addAttribute("auth", Authentication.build("", ""));

    return "login";
  }
  
  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap model) {

    model.addAttribute("auth", Authentication.build("", ""));

    return "logout";
  }

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public String login() {

    return "redirect:ausgaben/kategorieReport";
  }

  public Authentication getAuthentification() {
    return authentification;
  }

  public void setAuthentification(Authentication authentification) {
    this.authentification = authentification;
  }

}
