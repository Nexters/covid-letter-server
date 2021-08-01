package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatResponse {

  private String sended; 
  private String sended_yet; 

  private String vaccinated_today;
  private String confirmed_today;
  private String cured_today;

  private String vaccinated_yes;
  private String confirmed_yes;
  private String cured_yes;

  // public LetterResponse(User source) {
  //   copyProperties(source, this);
  //   this.letters = source.letters();
  // }
}

    