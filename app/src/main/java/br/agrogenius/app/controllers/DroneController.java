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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.agrogenius.app.models.Drone;
import br.agrogenius.app.models.LicencaVoo;
import br.agrogenius.app.repositories.DroneRepository;
import br.agrogenius.app.repositories.LicencaVooRepository;
import br.agrogenius.app.repositories.TelemetriaRepository;
import jakarta.persistence.EntityManager;

@Controller
@RequestMapping("/drone")
public class DroneController {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private LicencaVooRepository licencaVooRepository;

    @Autowired
    private TelemetriaRepository telemetriaRepository;

    @GetMapping("")
    public ModelAndView getDrones() {
        ModelAndView model = new ModelAndView("drone/index");
        List<Drone> listDrone = droneRepository.findAll();
        model.addObject("drones", listDrone);
        return model;
    }

    @GetMapping("/usuario")
    public ModelAndView getDronesUsuario() {
        ModelAndView model = new ModelAndView("drone/usuario");
        List<Drone> listDrone = droneRepository.findAll();
        model.addObject("drones", listDrone);
        return model;
    }

    @GetMapping("/create")
    public ModelAndView createDrone() {
        ModelAndView model = new ModelAndView("drone/create");
        List<LicencaVoo> listLicencaVoo = licencaVooRepository.findAll();
        model.addObject("licencas", listLicencaVoo);
        model.addObject("drone", new Drone());
        return model;
    }

    @PostMapping("/create")
    public String createDrone(@ModelAttribute("drone") Drone drone, BindingResult result) {
        if (result.hasErrors()) {
            return "drone/create";
        }
        droneRepository.save(drone);
        return "redirect:/drone";
    }

    @GetMapping("/delete/{id}")
    public String deleteDrone(@PathVariable("id") Long id, RedirectAttributes redirectAttributes) {
        Drone drone = droneRepository.findById(id).orElse(null);

        if (hasHistoricoVooOrTelemetria(drone)) {
            redirectAttributes.addFlashAttribute("mensagem", "Não é possível excluir o drone porque está associado a um histórico de voo ou telemetria.");
            return "redirect:/drone";
        }

        droneRepository.deleteById(id);
        return "redirect:/drone";
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditDronePage(@PathVariable("id") Long id) {
        ModelAndView mav = new ModelAndView("drone/edit");
        Drone drone = droneRepository.getById(id);
        List<LicencaVoo> listLicencaVoo = licencaVooRepository.findAll();
        mav.addObject("drone", drone);
        mav.addObject("licencas", listLicencaVoo);
        return mav;
    }

    @PostMapping("/edit/{id}")
    public String editDrone(@PathVariable("id") Long id, @ModelAttribute("drone") Drone droneAtualizado, BindingResult result) {
        if (result.hasErrors()) {
            return "drone/edit";
        } else {
            Drone droneExistente = droneRepository.getById(id);
            droneExistente.setModelo(droneAtualizado.getModelo());
            droneExistente.setNumeroSerie(droneAtualizado.getNumeroSerie());
            droneExistente.setDataCompra(droneAtualizado.getDataCompra());
            droneExistente.setCapacidadeBateria(droneAtualizado.getCapacidadeBateria());
            droneExistente.setCapacidadeCarga(droneAtualizado.getCapacidadeCarga());

            droneRepository.save(droneExistente);
            return "redirect:/drone";
        }
    }

    private boolean hasHistoricoVooOrTelemetria(Drone drone) {
        return existsHistoricoVooByDrone(drone) || telemetriaRepository.existsByDrone(drone);
    }

    @Autowired
    private EntityManager entityManager;

    private boolean existsHistoricoVooByDrone(Drone drone) {
        return entityManager.createQuery("SELECT COUNT(h) > 0 FROM HistoricoVoo h WHERE h.drone = :drone", Boolean.class)
                .setParameter("drone", drone)
                .getSingleResult();
    }
}
