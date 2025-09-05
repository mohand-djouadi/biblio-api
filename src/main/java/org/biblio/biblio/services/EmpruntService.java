package org.biblio.biblio.services;

import org.biblio.biblio.models.Emprunt;
import org.biblio.biblio.models.EmpruntId;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.repositories.EmpruntsRepository;
import org.biblio.biblio.repositories.LivreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.biblio.biblio.models.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class EmpruntService {

    @Autowired
    private EmpruntsRepository empruntsRepository;

    @Autowired
    private LivreRepository livreRepository;

    public List<Emprunt> getUserEmprunts(Long userId) {
        return this.empruntsRepository.findByUtilisateurId(userId);
    }

    public Emprunt createEmprunts(Map<String,Object> empruntInfo, User user) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Livre livre = livreRepository.findById(
                Long.parseLong(empruntInfo.get("id").toString())
            ).orElseThrow(() -> new NullPointerException("livre not found"));
            Emprunt emprunt = Emprunt.builder()
                .id(
                    EmpruntId.builder()
                        .utilisateurId(user.getId())
                        .livreId(livre.getId())
                        .build()
                )
                .livre(livre)
                .utilisateur(user)
                .startDate(formatter.parse(empruntInfo.get("startDate").toString()))
                .endDate(formatter.parse(empruntInfo.get("endDate").toString()))
                .build();
            return this.empruntsRepository.save(emprunt);
        } catch(ParseException e) {
            System.out.println("error : " + e.getMessage());
            throw new IllegalArgumentException("Format de date invalide. Utilisez 'yyyy-MM-dd'.");
        }
    }

}
