package org.biblio.biblio.controllers;

import org.biblio.biblio.models.Commande;
import org.biblio.biblio.services.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/commande")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @GetMapping(value = "/commande")
    public List<Commande> getAllCommandes() {
        return this.commandeService.getAllCommandes();
    }

}
