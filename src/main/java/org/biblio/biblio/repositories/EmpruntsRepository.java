package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Emprunt;
import org.biblio.biblio.models.EmpruntId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmpruntsRepository extends JpaRepository<Emprunt, EmpruntId> {

    Optional<Emprunt> findByLivreIdAndUtilisateurId(Long livreId, Long userId);
    List<Emprunt> findByLivreId(Long livreId);
    List<Emprunt> findByUtilisateurId(Long userId);

}
