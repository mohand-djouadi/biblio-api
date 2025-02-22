package org.biblio.biblio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.biblio.biblio.models.Commande;
import org.biblio.biblio.repositories.UserRepository;
import org.biblio.biblio.services.CommandeService;
import org.biblio.biblio.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.biblio.biblio.models.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/commande")
public class CommandeController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public List<Commande> getAllCommandes() {
        return this.commandeService.getAllCommandes();
    }

    @PostMapping(value = "add-commande")
    public ResponseEntity<Map<String,Object>> addCommande(
        @RequestBody Map<String,Object> requestBody,
        HttpServletRequest request
    ) {
        Map<String,Object> response = new HashMap<>();
        User user = this.userRepository.findByUserName(
            this.jwtService.exractUsername(
                request.getHeader("Authorization").substring(7)
            )
        );
        List<Map<String,Object>> products = (List<Map<String,Object>>) requestBody.get("livres");
        Commande commande = this.commandeService.createCommande(products, user);
        response.put("id", commande.getId());
        response.put("user", user.getId());
        response.put("total price", commande.getTotalPrice());
        return ResponseEntity.status(201).body(response);
    }
}
