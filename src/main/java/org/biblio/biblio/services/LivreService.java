package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.repositories.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivreService {

    private LivreRepository livreRepository;

    public List<Livre> getAllLivre() {
        return livreRepository.findAll();
    }
    public Optional<Livre> getLivreById(Long id) {
        return livreRepository.findById(id);
    }
    public List<Livre> getLivreByTitle(String title) {
        return livreRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Livre> getLivreByAuthor(String author) {
        return livreRepository.findByAuteursContaining(author);
    }
    public List<Livre> getLivreByCategory(String category) {
        return livreRepository.findByCategoryContaining(category);
    }
    public List<Livre> getLiveByEmprunter(Long emprunter) {
        return livreRepository.findByEmpruntersUtilisateur(emprunter);
    }

}
