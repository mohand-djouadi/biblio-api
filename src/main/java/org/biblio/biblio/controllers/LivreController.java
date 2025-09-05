package org.biblio.biblio.controllers;

import lombok.AllArgsConstructor;
import org.biblio.biblio.DTOs.LivreDTO;
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
    public List<LivreDTO> getAllLivres() {
        return livreService.getAllLivre();
    }

    @GetMapping(value = "/detail")
    public LivreDTO getLivreDetail(@RequestParam Long id) {
        return this.livreService.getLivreById(id);
    }

    @GetMapping(value = "/emprunter")
    public List<Livre> getLivreEmprunter(@RequestParam Long emprunter) {
        return livreService.getLiveByEmprunter(emprunter);
    }

}
