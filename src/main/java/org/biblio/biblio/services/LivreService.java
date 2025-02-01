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
        return livreRepository.findByAuteursNameContaining(author);
    }
    public List<Livre> getLivreByCategory(String category) {
        return livreRepository.findByCategoryContaining(category);
    }
    public List<Livre> getLiveByEmprunter(Long emprunter) {
        return livreRepository.findByEmpruntersUtilisateurId(emprunter);
    }

    public Livre updateRate(Long livre_id, double rate) {
        Optional<Livre> optionalLivre = livreRepository.findById(livre_id);
        Livre livre = optionalLivre.orElse(null);
        if (livre == null) {
            throw new IllegalArgumentException("livre not found");
        }
        livre.setRate(rate);
        livreRepository.save(livre);
        return livre;
    }

}
