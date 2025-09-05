package org.biblio.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.biblio.biblio.models.Livre;

import java.util.List;
import java.util.Optional;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findAll();
    Optional<Livre> findById(Long id);
    List<Livre> findByTitleContaining(String title);
    List<Livre> findByTitleContainingIgnoreCase(String title);
    List<Livre> findByCategoryContaining(String category);
    List<Livre> findByEmpruntersUtilisateurId(Long user_id);
    List<Livre> findByLivreAuteursAuteurId(Long auteur_id);
    List<Livre> findByLivresCommandeId(Long commande_id);
}
