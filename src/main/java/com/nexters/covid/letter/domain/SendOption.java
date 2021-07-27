package com.nexters.covid.letter.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "send_option")
@NoArgsConstructor
public class SendOption {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String text;

  private Long covidStat;

  @OneToMany(mappedBy = "sendOption", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private final List<Question> questions = new ArrayList<>();

  public List<Question> questions() {
    return questions;
  }
}
