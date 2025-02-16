package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Emprunt;
import org.biblio.biblio.models.EmpruntId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntsRepository extends JpaRepository<Emprunt, EmpruntId> {

}
