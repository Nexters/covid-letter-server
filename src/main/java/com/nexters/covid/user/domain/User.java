package com.nexters.covid.user.domain;

import com.nexters.covid.base.BaseEntity;
import com.nexters.covid.user.api.dto.LoginRequest;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class User extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String email;

  private String identifier;

  public User(LoginRequest loginRequest) {
    this.email = loginRequest.getEmail();
    this.identifier = loginRequest.getIdentifier();
  }

  public User() {

  }
}
