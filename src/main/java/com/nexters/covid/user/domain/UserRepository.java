package com.nexters.covid.user.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findUserByIdentifier(String identifier);

  Optional<User> findUserByEmail(String email);
}
