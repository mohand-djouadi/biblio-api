package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    List<Auteur> findAll();
    Auteur findById(long id);
    List<Auteur> findByNameContaining(String name);
    List<Auteur> findByNameContainingIgnoreCase(String name);
    List<Auteur> findByCountry(String country);
    List<Auteur> findByLivres(Long livre_id);
}
