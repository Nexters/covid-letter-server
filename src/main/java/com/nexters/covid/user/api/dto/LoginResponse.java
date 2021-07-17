package com.nexters.covid.user.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {

  // TODO: 클라이언트에서 받을 정보들 정하기
  private String accessToken;
}
