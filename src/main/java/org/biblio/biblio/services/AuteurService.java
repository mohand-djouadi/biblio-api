package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
import org.biblio.biblio.DTOs.AuteurDTO;
import org.biblio.biblio.models.Auteur;
import org.biblio.biblio.repositories.AuteurRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuteurService {

    private AuteurRepository auteurRepository;

    public List<Auteur> getAllAuteurs() {
        return auteurRepository.findAll();
    }
    public AuteurDTO getAuteurByLivre(Long livre) {
        Auteur auteur = auteurRepository.GetAuteurDetailByLivre(livre)
            .orElseThrow(() -> new RuntimeException("Auteur not found"));
        return AuteurDTO.builder()
            .id(auteur.getId())
            .rate(auteur.getRate())
            .description(auteur.getDescription())
            .name(auteur.getName())
            .imageUrl(auteur.getImageUrl())
            .country(auteur.getCountry())
            .build();
    }

}
