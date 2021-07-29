package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.SendOption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponse {

  private String text;
  private Long covidStat;

  public OptionResponse(SendOption source) {
    copyProperties(source, this);
  }
}
