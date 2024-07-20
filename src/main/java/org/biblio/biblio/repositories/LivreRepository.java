package org.biblio.biblio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.biblio.biblio.models.Livre;

import java.util.List;

public interface LivreRepository extends JpaRepository<Livre, Long> {
    List<Livre> findAll();
    Livre findById(long id);
    List<Livre> findByTitleContaining(String title);
    List<Livre> findByTitleContainingIgnoreCase(String title);
    List<Livre> findByAuteursContaining(String auteur);
    List<Livre> findByCategoryContaining(String category);
    List<Livre> findByEmpruntersUtilisateur(Long user_id);
}
