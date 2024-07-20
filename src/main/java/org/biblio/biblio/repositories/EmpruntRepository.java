package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Emprunt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
}
