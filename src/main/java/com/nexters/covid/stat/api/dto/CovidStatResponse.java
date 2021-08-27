package com.nexters.covid.stat.api.dto;

import com.nexters.covid.stat.domain.CovidStat;
import lombok.Getter;
import lombok.Setter;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
public class CovidStatResponse {

  private String date;

  private String vaccinated;
  private String confirmed;
  private String cured;

  private String vaccinatedPer;
  private String confirmedPer;
  private String curedPer;

  private long lettersSend;
  private long lettersPending;

  public CovidStatResponse(CovidStat source, CovidStat prevStat, long lettersCount,
      long lettersPending) {
    copyProperties(source, this);
    this.curedPer = calcPerChange(source.getCured(), prevStat.getCured());
    this.vaccinatedPer = calcPerChange(source.getVaccinated(), prevStat.getVaccinated());
    this.confirmedPer = calcPerChange(source.getConfirmed(), prevStat.getConfirmed());
    this.lettersSend = lettersCount - lettersPending;
    this.lettersPending = lettersPending;
  }

  private String calcPerChange(String today, String yesterday) {
    double differ = Double.parseDouble(today) - Double.parseDouble(yesterday);
    return String.valueOf(differ);
  }
}
