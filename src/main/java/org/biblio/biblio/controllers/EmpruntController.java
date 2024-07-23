package org.biblio.biblio.controllers;

import lombok.AllArgsConstructor;
import org.biblio.biblio.models.Emprunt;
import org.biblio.biblio.repositories.EmpruntRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/emprunts")
@AllArgsConstructor
public class EmpruntController {

    private EmpruntRepository empruntRepository;

    @GetMapping(value = "/")
    public List<Emprunt> getAll() {
        return empruntRepository.findAll();
    }
}
