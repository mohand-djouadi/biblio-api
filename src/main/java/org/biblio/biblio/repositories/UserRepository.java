package org.biblio.biblio.repositories;

import org.biblio.biblio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(long id);
    List<User> findByLivresLivre(Long livre_id);
}