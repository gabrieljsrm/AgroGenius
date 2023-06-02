package br.agrogenius.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrogenius.app.models.Drone;
import br.agrogenius.app.models.HistoricoVoo;

@Repository
public interface HistoricoVooRepository extends JpaRepository<HistoricoVoo, Long> {
    boolean existsByDrone(Drone drone);
}
