package br.agrogenius.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrogenius.app.models.Drone;
import br.agrogenius.app.models.LicencaVoo;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>{
	
	List<Drone> findByLicencaVoo(LicencaVoo licencaVoo);
}
