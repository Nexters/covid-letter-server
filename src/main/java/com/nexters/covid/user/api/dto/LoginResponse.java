package com.nexters.covid.user.api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

  // TODO: 토큰 만료 시간 설정 파일로 빼기
  private String accessToken;
  private long tokenExpirationTime;

  public LoginResponse(String accessToken) {
    this.accessToken = accessToken;
    this.tokenExpirationTime = 60 * 60 * 12;
  }
}
