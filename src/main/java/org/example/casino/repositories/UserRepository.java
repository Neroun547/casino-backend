package org.example.casino.repositories;

import org.example.casino.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Object findByUsernameOrEmail(String username, String email);
}
