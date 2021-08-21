package com.nexters.covid.stat.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidStatRepository extends JpaRepository<CovidStat, Long> {
    CovidStat findByDate(String date);
}
