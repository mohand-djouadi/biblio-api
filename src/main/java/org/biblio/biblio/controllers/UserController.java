package org.biblio.biblio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.Response;
import org.apache.tomcat.util.net.IPv6Utils;
import org.biblio.biblio.CustomExceptions.UsernameOrEmailUsedException;
import org.biblio.biblio.DTOs.ChangePasswordDTO;
import org.biblio.biblio.DTOs.RateLivreDTO;
import org.biblio.biblio.DTOs.UserDTO;
import org.biblio.biblio.models.Livre;
import org.biblio.biblio.services.JwtService;
import org.biblio.biblio.services.LivreService;
import org.biblio.biblio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import org.biblio.biblio.models.User;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private LivreService livreService;

    @GetMapping(value = "/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws IllegalAccessException {
        Map<String, Object> response = new HashMap<>();
        try {
            User verifiedUser = (User)userService.verify(user);
            String token = jwtService.createToken(user);
            UserDTO userResponse = new UserDTO().getUserDTO(verifiedUser);
            Field[] userFields = UserDTO.class.getDeclaredFields();
            for (Field field : userFields) {
                field.setAccessible(true);
                response.put(field.getName(), field.get(userResponse));
            }
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (BadCredentialsException e) {
            System.out.println("username or password incorrect");
            response.put("error", "username or password incorrect");
            return ResponseEntity.status(401).body(response);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) throws IllegalAccessException {
        Map<String,Object> response = new HashMap<>();
        try {
            User newUser = userService.registerUser(user);
            String token = jwtService.createToken(newUser);
            UserDTO responseUser = new UserDTO().getUserDTO(newUser);
            Field[] fields = UserDTO.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                response.put(field.getName(), field.get(responseUser));
            }
            response.put("token", token);
            return ResponseEntity.ok(response);
        } catch (UsernameOrEmailUsedException e) {
            System.out.println("username or email already used");
            response.put("error", "username or email already used");
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<Map<String,Object>> changePassword(@RequestBody ChangePasswordDTO passwordData, HttpServletRequest request) {
        Map<String,Object> response = new HashMap<>();
        try {
            String jwtToken = request.getHeader("Authorization").substring(7);
            String username = jwtService.exractUsername(jwtToken);
            userService.changePassword(
                    passwordData.getOldPassword(),
                    passwordData.getNewPassword(),
                    username
            );
            response.put("message", "password updated successfully");
            return ResponseEntity.ok(response);
        } catch(IllegalArgumentException e) {
            System.out.println("old password incorrect");
            response.put("error", "old password incorrect");
            return ResponseEntity.status(400).body(response);
        } catch (Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }

    @PostMapping(value = "/rating")
    public ResponseEntity<Map<String,Object>> RateLivre(@RequestBody RateLivreDTO rateLivre) {
        Map<String,Object> response = new HashMap<>();
        try {
            Long livreId = rateLivre.getLivreId();
            double rate = rateLivre.getRate();
            Livre livre = livreService.updateRate(livreId, rate);
            response.put("message", "book " + livre.getTitle() + " Rated successfuly");
            return ResponseEntity.ok(response);
        } catch(IllegalArgumentException e) {
            response.put("error", "book not found");
            return ResponseEntity.status(404).body(response);
        } catch(Exception e) {
            System.out.println("error : " + e.getMessage());
            response.put("error", "Internal server error");
            return ResponseEntity.status(500).body(response);
        }
    }
}
