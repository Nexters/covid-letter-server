package com.nexters.covid.letter.domain.sendoption;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SendOptionRepository extends JpaRepository<SendOption, Long> {

  @Query("select o from SendOption o join fetch o.questions")
  List<SendOption> findAllJoinFetch();

  Optional<SendOption> findSendOptionById(Long sendOptionId);
}
