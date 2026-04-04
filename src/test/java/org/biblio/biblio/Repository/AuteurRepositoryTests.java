package org.biblio.biblio.Repository;

import org.biblio.biblio.models.Auteur;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.repositories.AuteurRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class AuteurRepositoryTests {

    @Autowired
    private AuteurRepository auteurRepository;
    @Autowired
    private LivreRepository livreRepository;

    @Test
    public void AutorRepository_Save_ReturnSavedAuteur() {
        Auteur savedAuteur = this.auteurRepository.save(
            Auteur.builder()
                .name("victor hugo")
                .imageUrl("https://www.image.victor.hugo")
                .description("french writer how wrote les mesirables")
                .country("France")
                .build()
        );
        Assertions.assertNotNull(savedAuteur);
        Assertions.assertTrue(savedAuteur.getId() > 0);
    }
    @Test
    public void AuteurRepository_FindAll_ReturnAllAuteurs() {
        Auteur auteurf = this.auteurRepository.save(
            Auteur.builder()
                .name("victor hugo")
                .imageUrl("https://www.image.victor.hugo")
                .description("french writer how wrote les mesirables")
                .country("France")
                .build()
        );
        Auteur auteurs = this.auteurRepository.save(
            Auteur.builder()
                .name("emanuel kantt")
                .imageUrl("https://www.image.emanuel.kuntt")
                .description("german writer")
                .country("Germany")
                .build()
        );
        List<Auteur> auteurList = this.auteurRepository.findAll();
        Assertions.assertNotNull(auteurList);
        Assertions.assertEquals(2, auteurList.size());
    }
    @Test
    public void AuteurRepository_FindById_ReturnAuteur() {
        Auteur auteurf = this.auteurRepository.save(
            Auteur.builder()
                .name("victor hugo")
                .imageUrl("https://www.image.victor.hugo")
                .description("french writer how wrote les mesirables")
                .country("France")
                .build()
        );
        Auteur fetchedAuteurf = this.auteurRepository.findById(auteurf.getId()).get();
        Assertions.assertNotNull(fetchedAuteurf);
        Assertions.assertEquals(auteurf.getName(), fetchedAuteurf.getName());
    }
}
