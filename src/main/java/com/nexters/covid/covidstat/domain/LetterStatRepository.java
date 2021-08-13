package com.nexters.covid.covidstat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LetterStatRepository extends JpaRepository<LetterStat, String> {

  @Query("SELECT COUNT(CASE WHEN SENDED = '1' THEN 1 END) sended, 
          COUNT(CASE WHEN SENDED = '0' THEN 1 END) sended_yet
          FROM LETTER")
  LetterStat findLetterStat();
}