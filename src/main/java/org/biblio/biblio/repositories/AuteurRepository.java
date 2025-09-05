package org.biblio.biblio.repositories;

import org.biblio.biblio.models.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AuteurRepository extends JpaRepository<Auteur, Long> {
    List<Auteur> findAll();
    Optional<Auteur> findById(Long id);
    List<Auteur> findByNameContaining(String name);
    List<Auteur> findByNameContainingIgnoreCase(String name);
    List<Auteur> findByCountry(String country);
    List<Auteur> findByAuteurLivreLivreId(Long livreId);

    @Query(
        value = """
        select * from auteur a
        join livre_auteur la on la.auteur_id = a.id where
        la.principal = true and la.livre_id = :livreId 
        """, nativeQuery = true
    )
    Optional<Auteur> GetAuteurDetailByLivre(@Param("livreId") Long livreId);

}
