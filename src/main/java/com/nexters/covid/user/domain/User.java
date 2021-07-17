package com.nexters.covid.user.domain;

import com.nexters.covid.base.BaseEntity;
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

  public User(String email, String identifier) {
    this.email = email;
    this.identifier = identifier;
  }

  public User() {

  }
}
