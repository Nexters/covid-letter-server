package com.nexters.covid.letter.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexters.covid.letter.domain.question.Question;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

  @Test
  @DisplayName("[Question] id 일치 여부 메소드 테스트")
  void isMatchTest() {
    Question question = new Question(1L, "테스트 질문");

    assertThat(question.isMatch(1L)).isTrue();
    assertThat(question.isMatch(2L)).isFalse();
  }
}
