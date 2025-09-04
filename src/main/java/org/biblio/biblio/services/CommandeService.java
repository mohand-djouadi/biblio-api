package org.biblio.biblio.services;

import org.biblio.biblio.models.*;
import org.biblio.biblio.repositories.CommandeLivreRepository;
import org.biblio.biblio.repositories.CommandeRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CommandeService {

    @Autowired
    private CommandeLivreRepository commandeLivreRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private LivreRepository livreRepository;

    public List<Commande> getAllCommandes() {
        return this.commandeRepository.findAll();
    }

    public List<Commande> getUserCommandes(Long userId) {
        return this.commandeRepository.findByUserId(userId);
    }

    public Commande getCommande(Long id) {
        return this.commandeRepository.findById(id).orElseThrow(() -> null);
    }

    public Commande createEmptyCommande(User user) {
        return this.commandeRepository.save(
            Commande.builder()
                .user(user)
                .build()
        );
    }

    public Commande createCommande(List<Map<String,Object>> livres, User user) {
        Commande commande = this.createEmptyCommande(user);
        System.out.println("id commande : " + commande.getId());
        Double totalPrice = 0.0;
        for (Map<String,Object> livre: livres) {
            Livre currentLivre = this.livreRepository.findById(
                Long.parseLong(livre.get("id").toString())
            ).orElseThrow(() -> new NullPointerException("livre not found"));
            System.out.println("id livre : " + currentLivre.getId());
            CommandeLivreId commandeLivreId = CommandeLivreId.builder()
                .livreId(currentLivre.getId())
                .commandeId(commande.getId()).build();
            this.commandeLivreRepository.save(
                    CommandeLivre.builder()
                        .id(commandeLivreId)
                        .commande(commande)
                        .livre(currentLivre)
                        .quantity(Integer.parseInt(livre.get("quantity").toString()))
                        .build()
            );
            totalPrice += currentLivre.getSellPrice() * Integer.parseInt(livre.get("quantity").toString());
        }
        commande.setTotalPrice(totalPrice);
        this.commandeRepository.save(commande);
        return commande;
    }

}
