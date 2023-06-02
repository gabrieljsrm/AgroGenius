package br.agrogenius.app.controllers;

import java.time.LocalDateTime;
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
import br.agrogenius.app.models.Telemetria;
import br.agrogenius.app.repositories.DroneRepository;
import br.agrogenius.app.repositories.TelemetriaRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/telemetria")
public class TelemetriaController {

    @Autowired
    private TelemetriaRepository telemetriaRepository;

    @Autowired
    private DroneRepository droneRepository;

    @GetMapping("")
    public ModelAndView getTelemetrias() {
        ModelAndView model = new ModelAndView("telemetria/index");
        List<Telemetria> listTelemetria = telemetriaRepository.findAll();
        model.addObject("telemetrias", listTelemetria);
        return model;
    }
    
    @GetMapping("/usuario")
    public ModelAndView getTelemetriasUsuario() {
        ModelAndView model = new ModelAndView("telemetria/usuario");
        List<Telemetria> listTelemetria = telemetriaRepository.findAll();
        model.addObject("telemetrias", listTelemetria);
        return model;
    }

    @GetMapping("/create")
    public ModelAndView createTelemetria() {
        ModelAndView model = new ModelAndView("telemetria/create");
        List<Drone> listDrone = droneRepository.findAll();
        model.addObject("drones", listDrone);
        model.addObject("telemetria", new Telemetria());
        return model;
    }

    @PostMapping("/create")
    public String createTelemetria(@Valid @ModelAttribute("telemetria") Telemetria telemetria, BindingResult result) {
        if (result.hasErrors()) {
            return "telemetria/create";
        }

        Drone drone = droneRepository.getById(telemetria.getDrone().getId());
        if (drone == null) {
            return "telemetria/create";
        }

        telemetria.setDrone(drone);
        telemetria.setDataHora(LocalDateTime.now());  // Adição da data e hora atuais
        telemetriaRepository.save(telemetria);
        return "redirect:/telemetria";
    }


    @GetMapping("/delete/{id}")
    public String deleteTelemetria(@PathVariable("id") Long id) {
        telemetriaRepository.deleteById(id);
        return "redirect:/telemetria";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditTelemetriaPage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("telemetria/edit");
        Telemetria telemetria = telemetriaRepository.getById(id);
        List<Drone> listDrone = droneRepository.findAll();
        mav.addObject("telemetria", telemetria);
        mav.addObject("drones", listDrone);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editTelemetria(@PathVariable("id") Long id, @Valid @ModelAttribute("telemetria") Telemetria telemetriaAtualizada, BindingResult result) {
        if (result.hasErrors()) {
            return "telemetria/edit";
        }

        Drone drone = droneRepository.getById(telemetriaAtualizada.getDrone().getId());
        if (drone == null) {
            return "telemetria/edit";
        }

        Telemetria telemetriaExistente = telemetriaRepository.getById(id);
        telemetriaExistente.setDrone(drone);
        telemetriaExistente.setLatitude(telemetriaAtualizada.getLatitude());
        telemetriaExistente.setLongitude(telemetriaAtualizada.getLongitude());
        telemetriaExistente.setVelocidade(telemetriaAtualizada.getVelocidade());
        telemetriaExistente.setDirecao(telemetriaAtualizada.getDirecao());

        telemetriaRepository.save(telemetriaExistente);
        return "redirect:/telemetria";
    }
}
