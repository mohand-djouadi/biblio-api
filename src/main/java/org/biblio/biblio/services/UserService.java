package org.biblio.biblio.services;

import lombok.AllArgsConstructor;
import org.biblio.biblio.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.biblio.biblio.models.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private AuthenticationManager authenticationManager;

    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

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
        return userRepository.findByEmpruntsLivreId(livre_id);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepository.findByUserName(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException(username);
        }
        return user;
    }
    public UserDetails verify(User user) {
        Authentication authUser = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
        );
        if (!authUser.isAuthenticated()) {
            throw new BadCredentialsException("Bad credentials");
        }
        return loadUserByUsername(user.getUsername());
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registredUser = userRepository.save(user);
        return registredUser;
    }

    public void changePassword(String oldPassword, String newPassword, String username) {
        User currentUser = (User)loadUserByUsername(username);
        if (!passwordEncoder.matches(oldPassword, currentUser.getPassword())) {
            System.out.println("old password incorrect");
            throw new IllegalArgumentException("old password incorrect");
        }
        currentUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(currentUser);
    }
}
