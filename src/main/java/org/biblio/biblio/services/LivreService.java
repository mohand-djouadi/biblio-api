package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
import org.biblio.biblio.DTOs.LivreDTO;
import org.biblio.biblio.models.Auteur;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.repositories.AuteurRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivreService {

    @Autowired
    private LivreRepository livreRepository;
    @Autowired
    private AuteurRepository auteurRepository;

    public List<LivreDTO> getAllLivre() {
        return this.livreRepository.findAll().stream().map(l ->
            LivreDTO.builder()
                .id(l.getId())
                .rate(l.getRate())
                .likes(l.getLikes())
                .sellPrice(l.getSellPrice())
                .imageUrl(l.getImageUrl())
                .description(l.getDescription())
                .borrowPrice(l.getBorrowPrice())
                .category(l.getCategory())
                .title(l.getTitle())
                .build()
        ).toList();
    }
    public LivreDTO getLivreById(Long id) {
        Livre livre = this.livreRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("livre not found"));
        Auteur auteur = auteurRepository.GetAuteurDetailByLivre(livre.getId())
            .orElseThrow(() ->new RuntimeException("author not found"));
        return LivreDTO.builder()
            .id(livre.getId())
            .rate(livre.getRate())
            .likes(livre.getLikes())
            .author(auteur.getName())
            .categories(List.of(livre.getCategory()))
            .sellPrice(livre.getSellPrice())
            .imageUrl(livre.getImageUrl())
            .description(livre.getDescription())
            .borrowPrice(livre.getBorrowPrice())
            .category(livre.getCategory())
            .title(livre.getTitle())
            .build();
    }
    public List<Livre> getLivreByTitle(String title) {
        return livreRepository.findByTitleContainingIgnoreCase(title);
    }
    public List<Livre> getLivreByCategory(String category) {
        return livreRepository.findByCategoryContaining(category);
    }
    public List<Livre> getLiveByEmprunter(Long emprunter) {
        return livreRepository.findByEmpruntersUtilisateurId(emprunter);
    }
    public List<Livre>getLivreByAuteur(Long auteur) {
        return livreRepository.findByLivreAuteursAuteurId(auteur);
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
