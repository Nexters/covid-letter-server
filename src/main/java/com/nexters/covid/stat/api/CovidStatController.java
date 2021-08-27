package com.nexters.covid.stat.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.stat.api.dto.CovidStatResponse;
import com.nexters.covid.stat.service.CovidStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CovidStatController {

  private final CovidStatService covidStatService;

  @GetMapping("/covidstat")
  public BaseResponse<CovidStatResponse> readTodayStat() {
    CovidStatResponse stat = covidStatService.readTodayStat();
    return new BaseResponse<>(200, 0, "", stat);
  }
}
