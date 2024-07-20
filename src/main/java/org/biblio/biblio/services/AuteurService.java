package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
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
    public Optional<Auteur> getAuteurById(Long id) {
        return auteurRepository.findById(id);
    }
    public List<Auteur> getAuteurByName(String name) {
        return auteurRepository.findByNameContainingIgnoreCase(name);
    }
    public List<Auteur> getAuteurByCountry(String country) {
        return auteurRepository.findByCountry(country);
    }
    public List<Auteur> getAuteurByLivre(Long livre) {
        return auteurRepository.findByLivresId(livre);
    }

}
