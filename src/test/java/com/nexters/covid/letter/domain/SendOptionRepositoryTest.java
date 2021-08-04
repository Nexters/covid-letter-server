package com.nexters.covid.letter.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class SendOptionRepositoryTest {

  @Autowired
  SendOptionRepository sendOptionRepository;

  @Test
  @DisplayName("[SendOption] join fetch 테스트")
  void fetchJoinTest() {
    List<SendOption> options = sendOptionRepository.findAllJoinFetch();

    assertThat(options.size()).isEqualTo(5);
  }
}
