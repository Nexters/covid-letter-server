package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.Question;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {

  private String text;

  public QuestionResponse(Question source) {
    copyProperties(source, this);
  }
}
