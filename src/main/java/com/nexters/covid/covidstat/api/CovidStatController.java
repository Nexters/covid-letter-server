package com.nexters.covid.covidstat.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.covidstat.api.dto.StatResponse;
import com.nexters.covid.covidstat.service.CovidStatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@AllArgsConstructor
public class CovidStatController {
    private final CovidStatService covidStatService;

    @GetMapping("/stat")
    public BaseResponse<StatResponse> getStat() throws IOException {
        StatResponse stats = covidStatService.getStat();

        return new BaseResponse<>(200, 0, "", stats);
    }
}