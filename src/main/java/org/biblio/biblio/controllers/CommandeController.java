package org.biblio.biblio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.biblio.biblio.models.Commande;
import org.biblio.biblio.repositories.CommandeRepository;
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
    private CommandeRepository commandeRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping(value = "/")
    public ResponseEntity<?> getUserCommandes(HttpServletRequest request) {
        Map<String,Object> response = new HashMap<>();
        try {
            Long userId = this.userRepository.findByUserName(
                    this.jwtService.exractUsername(
                            request.getHeader("Authorization").substring(7)
                    )
            ).getId();
            if (userId == null) {
                response.put("error", "user not found");
                return ResponseEntity.status(400).body(response);
            }
            List<Commande> commandes = this.commandeService.getUserCommandes(userId);
            return ResponseEntity.status(200).body(commandes);
        } catch(Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "add-commande")
    public ResponseEntity<Map<String,Object>> addCommande(
        @RequestBody Map<String,Object> requestBody,
        HttpServletRequest request
    ) {
        Map<String,Object> response = new HashMap<>();
        try {
            User user = this.userRepository.findByUserName(
                    this.jwtService.exractUsername(
                            request.getHeader("Authorization").substring(7)
                    )
            );
            if (user == null) {
                response.put("error", "user not found");
                return ResponseEntity.status(400).body(response);
            }
            List<Map<String,Object>> products = (List<Map<String,Object>>) requestBody.get("livres");
            Commande commande = this.commandeService.createCommande(products, user);
            response.put("id", commande.getId());
            response.put("user", user.getId());
            response.put("totalPrice", commande.getTotalPrice());
            return ResponseEntity.status(201).body(response);
        } catch(NullPointerException e) {
            response.put("error", "livre not found");
            return ResponseEntity.status(400).body(response);
        } catch(Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal srver error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PutMapping(value = "/cancel/{id}")
    public ResponseEntity<?> cancelCommande(@PathVariable Long id, HttpServletRequest request) {
        Map<String,Object> response = new HashMap<>();
        User user = this.userRepository.findByUserName(
                this.jwtService.exractUsername(
                        request.getHeader("Authorization").substring(7)
                )
        );
        if (user == null) {
            response.put("error", "user not found");
            return ResponseEntity.status(400).body(response);
        }
        Commande commande = this.commandeService.getCommande(id);
        if (commande == null) {
            response.put("error", "commande not found");
            return ResponseEntity.status(400).body(response);
        }
        commande.setStatus("annuler");
        return ResponseEntity.status(201).body(this.commandeRepository.save(commande));
    }
}