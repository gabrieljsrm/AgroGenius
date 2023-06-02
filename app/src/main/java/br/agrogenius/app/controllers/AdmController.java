package br.agrogenius.app.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AdmController {
    
	@RequestMapping("/adm")
	public String home(Model model) {
		model.addAttribute("mensagem", "Bem vindo ao Futuro - Acesso de Admnistrador");

		return "adm";
	}
}
