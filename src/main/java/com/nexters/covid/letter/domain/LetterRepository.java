package com.nexters.covid.letter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {

  List<Letter> findLettersByEmailOrderByCreatedDateDesc(String email);

  Optional<Letter> findLetterByEncryptedId(String encryptedId);
}
