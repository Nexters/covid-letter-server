package com.nexters.covid.letter.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {

  List<Question> findQuestionsBySendOptionIdEqualsOrSendOptionIdEquals(Long optionId, Long commonOptionId);
}
