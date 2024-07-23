package org.biblio.biblio.controllers;

import lombok.AllArgsConstructor;
import org.biblio.biblio.models.Auteur;
import org.biblio.biblio.services.AuteurService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/auteurs")
public class AuteurController {

    private AuteurService auteurService;

    @GetMapping(value = "/")
    public List<Auteur> getAll() {
        return auteurService.getAllAuteurs();
    }

}
