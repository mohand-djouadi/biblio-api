package org.biblio.biblio.controllers;

import lombok.AllArgsConstructor;
import org.biblio.biblio.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.biblio.biblio.models.User;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(value = "/")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
