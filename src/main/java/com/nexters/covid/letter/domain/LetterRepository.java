package com.nexters.covid.letter.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LetterRepository extends JpaRepository<Letter, Long> {

  @EntityGraph(attributePaths = "sendOption", type = EntityGraphType.FETCH)
  List<Letter> findLettersByEmailOrderByCreatedDateDesc(@Param("email") String email);

  @EntityGraph(attributePaths = "sendOption", type = EntityGraphType.FETCH)
  Optional<Letter> findLetterByEncryptedId(String encryptedId);

  List<Letter> findLetterByState(State state);

  @Query("select l from Letter l where l.sendOption.id = :sendOptionId and l.state = :state")
  List<Letter> findLettersBySendOptionIdAndState(@Param("sendOptionId") Long sendOptionId, @Param("state") State state);

  @Modifying
  @Query("UPDATE Letter l set l.state = 'SEND' WHERE l.encryptedId = :encryptedId")
  int updateLetterState(@Param("encryptedId") String encryptedId);
}
