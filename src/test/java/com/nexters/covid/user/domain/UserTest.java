package com.nexters.covid.user.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.nexters.covid.user.api.dto.LoginRequest;
import java.time.LocalDateTime;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class UserTest {

  @Test
  @DisplayName("[User] 마지막 로그인 시간, 로그인 시점 시작으로 업데이트 확인 테스트")
  public void updateLastLoginTimeTest() {
    User user = new User(new LoginRequest("email", "name", "identifier"));
    LocalDateTime initTime = user.getUpdateDate();

    user.updateLastLoginTime();

    assertThat(initTime).isNotEqualTo(user.getUpdateDate());
  }
}
