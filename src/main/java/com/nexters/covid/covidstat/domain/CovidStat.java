package com.nexters.covid.covidstat.domain;

import lombok.Data;
import lombok.Getter;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class CovidStat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String date;

    private String vaccinated;
    private String confirmed;
    private String cured;
}