package com.nexters.covid.stat.api.dto;

import lombok.Data;

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
