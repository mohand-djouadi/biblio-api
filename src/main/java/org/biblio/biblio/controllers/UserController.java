package org.biblio.biblio.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.biblio.biblio.DTOs.ChangePasswordDTO;
import org.biblio.biblio.DTOs.UserDTO;
import org.biblio.biblio.services.JwtService;
import org.biblio.biblio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(value = "/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User user) throws IllegalAccessException {
        User verifiedUser = (User)userService.verify(user);
        String token = jwtService.createToken(user);
        UserDTO userResponse = new UserDTO().getUserDTO(verifiedUser);
        Field[] userFields = UserDTO.class.getDeclaredFields();
        Map<String, Object> response = new HashMap<>();
        for (Field field : userFields) {
            field.setAccessible(true);
            response.put(field.getName(), field.get(userResponse));
        }
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Map<String, Object>> signup(@RequestBody User user) throws IllegalAccessException {
        User newUser = userService.registerUser(user);
        String token = jwtService.createToken(newUser);
        UserDTO responseUser = new UserDTO().getUserDTO(newUser);
        Field[] fields = UserDTO.class.getDeclaredFields();
        Map<String,Object> response = new HashMap<>();
        for (Field field : fields) {
            field.setAccessible(true);
            response.put(field.getName(), field.get(responseUser));
        }
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/change-password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO passwordData, HttpServletRequest request) {
        String jwtToken = request.getHeader("Authorization").substring(7);
        String username = jwtService.exractUsername(jwtToken);
        userService.changePassword(
                passwordData.getOldPassword(),
                passwordData.getNewPassword(),
                username
        );
        return ResponseEntity.ok("password updated successfully");
    }
}
