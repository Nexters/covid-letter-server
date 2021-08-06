package com.nexters.covid.letter.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class QuestionRepositoryTest {

  @Autowired
  QuestionRepository questionRepository;

  @Test
  @DisplayName("[QuestionRepository] 선택한 옵션에 따른 질문과 공통 질문 조회 테스트")
  void findQuestionByOptionTest() {
    long normalSendOptionId = 1L;
    long commonSendOptionId = 3L;
    List<Question> questions = questionRepository.findQuestionsBySendOptionIdEqualsOrSendOptionIdEquals(normalSendOptionId, commonSendOptionId);

    List<Long> sendOptionIds = questions.stream()
        .map(q -> q.getSendOption().getId())
        .collect(Collectors.toList());

    assertThat(sendOptionIds.contains(1L)).isTrue();
    assertThat(sendOptionIds.contains(3L)).isTrue();
  }
}
