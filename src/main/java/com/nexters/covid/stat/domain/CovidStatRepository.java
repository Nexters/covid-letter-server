package com.nexters.covid.stat.domain;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CovidStatRepository extends JpaRepository<CovidStat, Long> {
    CovidStat findByDate(String date);

    List<CovidStat> findTop2ByOrderByIdDesc();
}
