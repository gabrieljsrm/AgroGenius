package br.agrogenius.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import br.agrogenius.app.models.Drone;
import br.agrogenius.app.models.HistoricoVoo;
import br.agrogenius.app.repositories.DroneRepository;
import br.agrogenius.app.repositories.HistoricoVooRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/historicoVoo")
public class HistoricoVooController {

    @Autowired
    private HistoricoVooRepository historicoVooRepository;

    @Autowired
    private DroneRepository droneRepository;

    @GetMapping("")
    public ModelAndView getHistoricosVoo() {
        ModelAndView model = new ModelAndView("historicoVoo/index");
        List<HistoricoVoo> listHistoricoVoo = historicoVooRepository.findAll();
        model.addObject("historicosVoo", listHistoricoVoo);
        return model;
    }

    @GetMapping("/create")
    public ModelAndView createHistoricoVoo() {
        ModelAndView model = new ModelAndView("historicoVoo/create");
        List<Drone> listDrone = droneRepository.findAll();
        model.addObject("drones", listDrone);
        model.addObject("historicoVoo", new HistoricoVoo()); 
        model.addObject("droneId", null); 
        return model;
    }

    @PostMapping("/create")
    public String createHistoricoVoo(@Valid @ModelAttribute("historicoVoo") HistoricoVoo historicoVoo, BindingResult result, @RequestParam("droneId") Long droneId) {
        if (result.hasErrors()) {
            return "historicoVoo/create";
        }
        historicoVoo.setId(null); 
        Drone drone = droneRepository.getById(droneId);
        historicoVoo.setDrone(drone); 
        historicoVooRepository.save(historicoVoo);
        return "redirect:/historicoVoo";
    }

    @GetMapping("/delete/{id}")
    public String deleteHistoricoVoo(@PathVariable("id") Long id) {
        historicoVooRepository.deleteById(id);
        return "redirect:/historicoVoo";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditHistoricoVooPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("historicoVoo/edit");
        HistoricoVoo historicoVoo = historicoVooRepository.getById(id);
        List<Drone> listDrone = droneRepository.findAll();
        mav.addObject("historicoVoo", historicoVoo);
        mav.addObject("drones", listDrone);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editHistoricoVoo(@PathVariable("id") Long id, @Valid @ModelAttribute("historicoVoo") HistoricoVoo historicoVooAtualizado, BindingResult result, @RequestParam("droneId") Long droneId) {
        if (result.hasErrors()) {
            return "historicoVoo/edit";
        } else {
            HistoricoVoo historicoVooExistente = historicoVooRepository.getById(id);
            historicoVooExistente.setLatitudeInicial(historicoVooAtualizado.getLatitudeInicial());
            historicoVooExistente.setLongitudeInicial(historicoVooAtualizado.getLongitudeInicial());
            historicoVooExistente.setLatitudeFinal(historicoVooAtualizado.getLatitudeFinal());
            historicoVooExistente.setLongitudeFinal(historicoVooAtualizado.getLongitudeFinal());
            historicoVooExistente.setAltitudeMedia(historicoVooAtualizado.getAltitudeMedia());
            historicoVooExistente.setVelocidadeMedia(historicoVooAtualizado.getVelocidadeMedia());
            historicoVooExistente.setDataDecolagem(historicoVooAtualizado.getDataDecolagem());
            historicoVooExistente.setDataAterrisagem(historicoVooAtualizado.getDataAterrisagem());
            
            // Atualiza o drone do historicoVoo existente
            Drone drone = droneRepository.getById(droneId);
            historicoVooExistente.setDrone(drone);
            
            historicoVooRepository.save(historicoVooExistente);
            return "redirect:/historicoVoo";
        }
    }
}
