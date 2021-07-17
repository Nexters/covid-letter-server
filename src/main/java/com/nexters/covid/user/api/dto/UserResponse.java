package com.nexters.covid.user.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.user.domain.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

  private Long id;

  private String email;

  protected UserResponse() {}

  public UserResponse(User source) {
    copyProperties(source, this);
  }
}
