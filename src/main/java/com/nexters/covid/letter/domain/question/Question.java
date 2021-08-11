package com.nexters.covid.letter.domain.question;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexters.covid.letter.domain.sendoption.SendOption;
import javax.persistence.Entity;
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
public class Question {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "send_option_id")
  private SendOption sendOption;

  private String text;

  public boolean isMatch(Long questionId) {
    return id.equals(questionId);
  }
}
