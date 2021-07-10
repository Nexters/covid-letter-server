package com.nexters.covid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CovidApplication {

  public static void main(String[] args) {
    SpringApplication.run(CovidApplication.class, args);
  }

}
