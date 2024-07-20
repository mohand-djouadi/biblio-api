package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
import org.biblio.biblio.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.biblio.biblio.models.User;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username);
    }
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    public List<User> getUserByLivre(Long livre_id) {
        return userRepository.findByLivresLivreId(livre_id);
    }

}
