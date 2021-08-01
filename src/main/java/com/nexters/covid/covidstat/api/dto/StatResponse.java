package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatResponse {

  private String sended; 
  private String sendedYet; 

  private String vaccinatedToday;
  private String confirmedToday;
  private String curedToday;

  private String vaccinatedYes;
  private String confirmedYes;
  private String curedYes;

  public StatResponse(User source) {
    copyProperties(source, this);
    this.letters = source.letters();
  }
}

    