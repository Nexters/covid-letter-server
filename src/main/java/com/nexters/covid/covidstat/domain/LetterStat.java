package com.nexters.covid.covidstat.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Entity
public class LetterStat {

    private String sended;
    private String sendedYet;
}