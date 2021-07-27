package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.Question;
import com.nexters.covid.letter.domain.SendOption;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponse {

  private String text;
  private Long covidStat;
  private List<Question> questions;

  public OptionResponse(SendOption source) {
    copyProperties(source, this);
    this.questions = source.questions();
  }
}
