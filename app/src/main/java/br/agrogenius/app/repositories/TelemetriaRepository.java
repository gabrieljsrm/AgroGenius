package br.agrogenius.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrogenius.app.models.Drone;
import br.agrogenius.app.models.Telemetria;

@Repository
public interface TelemetriaRepository extends JpaRepository<Telemetria, Long> {
    boolean existsByDrone(Drone drone);
}
