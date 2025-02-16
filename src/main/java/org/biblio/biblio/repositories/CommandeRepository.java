package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Commande;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    List<Commande> findAll();
    Optional<Commande> findById(Long Id);
    List<Commande> findByUserId(Long userId);
    List<Commande> findByCommandesLivreId(Long livreId);

}
