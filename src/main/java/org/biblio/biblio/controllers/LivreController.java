package org.biblio.biblio.controllers;

import lombok.AllArgsConstructor;
import org.biblio.biblio.services.LivreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import org.biblio.biblio.models.Livre;

@RestController
@RequestMapping(value = "/livres")
@AllArgsConstructor
public class LivreController {

    private LivreService livreService;

    @GetMapping(value = "/")
    public List<Livre> getAllLivres() {
        return livreService.getAllLivre();
    }

    @GetMapping(value = "/")
    public List<Livre> getLivreEmprunter(@RequestParam Long emprunter) {
        return livreService.getLiveByEmprunter(emprunter);
    }

    @GetMapping(value = "/")
    public List<Livre> getLivreAuteur(@RequestParam Long auteur) {
        return livreService.getLiveByEmprunter(auteur);
    }

}
