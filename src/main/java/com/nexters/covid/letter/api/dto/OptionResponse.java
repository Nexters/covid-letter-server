package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.sendoption.SendOption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OptionResponse {

  private Long id;
  private String text;
  private Long covidStat;

  public OptionResponse(SendOption source) {
    copyProperties(source, this);
  }
}
