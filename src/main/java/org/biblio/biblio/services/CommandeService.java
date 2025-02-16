package org.biblio.biblio.services;

import org.biblio.biblio.models.Commande;
import org.biblio.biblio.models.CommandeLivre;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.repositories.CommandeLivreRepository;
import org.biblio.biblio.repositories.CommandeRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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

    public Commande createEmptyCommande() {
        return this.commandeRepository.save(
            Commande.builder().build()
        );
    }

    public Commande createCommande(List<Map<String,Object>> livres) {
        Commande commande = this.createEmptyCommande();
        Double totalPrice = 0.0;
        for (Map<String,Object> livre: livres) {
            this.commandeLivreRepository.save(
                CommandeLivre.builder()
                    .livre(this.livreRepository.findById(Long.parseLong(livre.get("id").toString()))
                       .orElseThrow(() -> new RuntimeException("Livre not found"))
                    )
                    .commande(commande)
                    .quantity(Integer.parseInt(livre.get("quantity").toString()))
                    .build()
            );
            totalPrice += Integer.parseInt(livre.get("price").toString());
        }
        return commande;
    }

}
