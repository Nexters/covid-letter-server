package com.nexters.covid.stat.api;

import com.nexters.covid.stat.api.dto.CovidStatResponse;
import com.nexters.covid.stat.domain.CovidStat;
import com.nexters.covid.stat.service.CovidStatService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/covidstat")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CovidStatController {

    private final CovidStatService covidStatService;

    @GetMapping("")
    public ResponseEntity<CovidStatResponse> readTodayStat () throws ParseException, ParserConfigurationException, IOException, SAXException {
        return ResponseEntity.ok(covidStatService.readTodayStat());
    }


    // 테스트 ----------------------------------------------------------------------------------------------
    @GetMapping("/covidOnly/{date}")
    public ResponseEntity<List<CovidStat>> readStats (@PathVariable String date) {
        System.out.println(date);
        return ResponseEntity.ok(covidStatService.readStats());
    }


    @GetMapping("/letters")
    public void readLetterStats () {
    }
}
