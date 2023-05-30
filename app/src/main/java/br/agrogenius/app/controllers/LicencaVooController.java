package br.agrogenius.app.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.agrogenius.app.models.LicencaVoo;
import br.agrogenius.app.repositories.LicencaVooRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/licencavoo")
public class LicencaVooController {

	@Autowired
    private LicencaVooRepository licencaVooRepository; 

    @GetMapping("")
    public ModelAndView getLicencas() {
        ModelAndView model = new ModelAndView("licencavoo/index");
        List<LicencaVoo> listLicencaVoo = licencaVooRepository.findAll();
        model.addObject("licencas", listLicencaVoo);
        return model; 
    }

    @GetMapping("/create")
    public ModelAndView createLicenca() {
        ModelAndView model = new ModelAndView("licencavoo/create");
        return model; 
    }

    @PostMapping("/create")
    public String createLicenca(@Valid @ModelAttribute("licenca") LicencaVoo licenca, BindingResult result) {
        if(result.hasErrors()) {
            return "licencavoo/create";
        }
        licencaVooRepository.save(licenca);
        return "redirect:/licencavoo";
    }

    @GetMapping("/delete/{id}")
    public String deleteLicenca(@PathVariable("id") Long id) {
        licencaVooRepository.deleteById(id);
        return "redirect:/licencavoo";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditLicencaPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("licencavoo/edit");
        LicencaVoo licenca = licencaVooRepository.getById(id);
        mav.addObject("licenca", licenca);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editLicenca(@PathVariable("id") Long id, @Valid @ModelAttribute("licenca") LicencaVoo licencaAtualizada, BindingResult result) {
        if (result.hasErrors()) {
            return "licencavoo/edit";
        } else {
            LicencaVoo licencaExistente = licencaVooRepository.getById(id);
            licencaExistente.setLicencavoo(licencaAtualizada.getLicencavoo());
            licencaExistente.setDataEmissao(licencaAtualizada.getDataEmissao());
            licencaExistente.setDataValidade(licencaAtualizada.getDataValidade());

            licencaVooRepository.save(licencaExistente);
            return "redirect:/licencavoo";
        }
    }

    }
	

