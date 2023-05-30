package br.agrogenius.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.agrogenius.app.models.LicencaVoo;

@Repository
public interface LicencaVooRepository extends JpaRepository<LicencaVoo, Long> {

}
