package de.eichstaedt.ui.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Scope(value=ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class IndexController {
	
	@RequestMapping("/index")
	public String index() {
		
		return "index";
	}

}
