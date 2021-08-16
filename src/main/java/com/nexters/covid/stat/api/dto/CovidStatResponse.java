package com.nexters.covid.stat.api.dto;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.stat.domain.CovidStat;
import lombok.Data;

import static org.springframework.beans.BeanUtils.copyProperties;

@Data
public class CovidStatResponse {
    private String date;

    private String vaccinated;
    private String confirmed;
    private String cured;

    private String vaccinatedPer;
    private String confirmedPer;
    private String curedPer;

    private int lettersSend;
    private int lettersPending;

}
