package com.nexters.covid.letter.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LetterRepository extends JpaRepository<Letter, Long> {

  List<Letter> findLettersByEmail(String email);
}
