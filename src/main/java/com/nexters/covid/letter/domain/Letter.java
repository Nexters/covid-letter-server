package com.nexters.covid.letter.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexters.covid.base.BaseEntity;
import com.nexters.covid.user.domain.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Letter extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private User user;

  private String letterTo;

  private String title;

  private String contents;

  private String email;

  @Enumerated(EnumType.STRING)
  private State state;

  @Enumerated(EnumType.STRING)
  private Sticker sticker;

  private String answer;

  private String questionId;

  private String encryptedId;
}
