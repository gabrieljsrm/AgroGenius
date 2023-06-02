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
import br.agrogenius.app.models.LicencaVoo;
import br.agrogenius.app.repositories.DroneRepository;
import br.agrogenius.app.repositories.LicencaVooRepository;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/drone")
public class DroneController {

	@Autowired
	private DroneRepository droneRepository;

	@Autowired
	private LicencaVooRepository licencaVooRepository;

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
		model.addObject("drone", new Drone()); // Adiciona um novo objeto Drone ao modelo
		model.addObject("licencaVooId", null); // Adiciona o atributo licencaVooId ao modelo
		return model;
	}

	@PostMapping("/create")
	public String createDrone(@Valid @ModelAttribute("drone") Drone drone, BindingResult result,
			@RequestParam("licencaVooId") Long licencaVooId) {
		if (result.hasErrors()) {
			return "drone/create";
		}
		drone.setId(null); // Define o ID como nulo antes de salvar o drone
		LicencaVoo licencaVoo = licencaVooRepository.getById(licencaVooId);
		drone.setLicencaVoo(licencaVoo); // Adiciona a associação licencaVoo ao drone
		droneRepository.save(drone);
		return "redirect:/drone";
	}

	@GetMapping("/delete/{id}")
	public String deleteDrone(@PathVariable("id") Long id) {
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
	public String editDrone(@PathVariable("id") Long id, @Valid @ModelAttribute("drone") Drone droneAtualizado,
			BindingResult result, @RequestParam("licencaVooId") Long licencaVooId) {
		if (result.hasErrors()) {
			return "drone/edit";
		} else {
			Drone droneExistente = droneRepository.getById(id);
			droneExistente.setModelo(droneAtualizado.getModelo());
			droneExistente.setNumeroSerie(droneAtualizado.getNumeroSerie());
			droneExistente.setDataCompra(droneAtualizado.getDataCompra());
			droneExistente.setCapacidadeBateria(droneAtualizado.getCapacidadeBateria());
			droneExistente.setCapacidadeCarga(droneAtualizado.getCapacidadeCarga());

			// Atualiza a licença de voo do drone existente
			LicencaVoo licencaVoo = licencaVooRepository.getById(licencaVooId);
			droneExistente.setLicencaVoo(licencaVoo);

			droneRepository.save(droneExistente);
			return "redirect:/drone";
		}
	}

}
