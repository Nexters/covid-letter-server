package com.nexters.covid.letter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LetterRepository extends JpaRepository<Letter, Long> {

  List<Letter> findLettersByEmailOrderByCreatedDateDesc(String email);

  Optional<Letter> findLetterByEncryptedId(String encryptedId);

  List<Letter> findLetterByState(State state);

  List<Letter> findLettersBySendOptionIdAndState(Long sendOptionId, State state);

  @Modifying
  @Query("UPDATE Letter l set l.state = 'SEND' WHERE l.encryptedId = :encryptedId")
  int updateLetterState(@Param("encryptedId") String encryptedId);
}
