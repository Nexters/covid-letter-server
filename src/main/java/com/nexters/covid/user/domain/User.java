package com.nexters.covid.user.domain;

import com.nexters.covid.base.BaseEntity;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.Letters;
import com.nexters.covid.user.api.dto.LoginRequest;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Embedded;
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

  private String name;

  private String identifier;

  @Embedded
  private Letters letters;

  public User(LoginRequest loginRequest) {
    this.email = loginRequest.getEmail();
    this.name = loginRequest.getName();
    this.identifier = loginRequest.getIdentifier();
  }

  public User() {

  }

  public void updateLastLoginTime() {
    this.updateDate = LocalDateTime.now();
  }

  public List<Letter> letters() {
    return letters.letters();
  }
}
