package org.biblio.biblio.repositories;

import org.biblio.biblio.models.CommandeLivre;
import org.biblio.biblio.models.CommandeLivreId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeLivreRepository extends JpaRepository<CommandeLivre, CommandeLivreId> {

}
