package com.nexters.covid.covidstat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidStatRepository extends JpaRepository<CovidStat, Long> {

// TODO: 쿼리에 증감량 계산식 넣기
  @Query("SELECT * FROM COVID_STAT WHERE DATE IN (:today_date, :yes_date)")
  CovidStat findByTwoDate(@Param("today_date") String today_date, @Param("yes_date") String yes_date);
}