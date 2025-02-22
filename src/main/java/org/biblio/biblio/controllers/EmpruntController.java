package org.biblio.biblio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.biblio.biblio.models.Emprunt;
import org.biblio.biblio.models.User;
import org.biblio.biblio.services.EmpruntService;
import org.biblio.biblio.services.JwtService;
import org.biblio.biblio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/emprunts")
public class EmpruntController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private EmpruntService empruntService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
    public ResponseEntity<?> getUserEmprunts(HttpServletRequest request) {
        Map<String,Object> response = new HashMap<>();
        try {
            User user = this.userService.getUserByUsername(
                jwtService.exractUsername(request.getHeader("Authorization").substring(7))
            );
            if (user == null) {
                response.put("error", "user not found");
                return ResponseEntity.status(400).body(response);
            }
            return ResponseEntity.status(200).body(this.empruntService.getUserEmprunts(user.getId()));
        } catch(Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/addemprunt")
    public ResponseEntity<?> addEmprunts(
        @RequestBody Map<String,Object> requestBody,
        HttpServletRequest request
    ) {
        Map<String,Object> response = new HashMap<>();
        try {
            User user = this.userService.getUserByUsername(
                jwtService.exractUsername(request.getHeader("Authorization").substring(7))
            );
            if (user == null) {
                response.put("error", "user not found");
                return ResponseEntity.status(400).body(response);
            }
            return ResponseEntity.status(200).body(this.empruntService.createEmprunts(requestBody, user));
        } catch (NullPointerException e) {
            response.put("error", "livre not found");
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal Server Error");
            return ResponseEntity.status(500).body(response);
        }
    }

}
