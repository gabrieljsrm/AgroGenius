package br.agrogenius.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrogenius.app.models.Drone;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long>{

}
