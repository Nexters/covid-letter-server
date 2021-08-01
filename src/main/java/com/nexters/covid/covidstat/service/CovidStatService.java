package com.nexters.covid.covidstat.service;

import com.nexters.covid.covidstat.api.dto.StatResponse;
import com.nexters.covid.covidstat.domain.CovidStat;
import com.nexters.covid.covidstat.domain.CovidStatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CovidStatService {

    private final CovidStatRepository covidStatRepository;
    private final LetterStatRepository letterStatRepository; 

    public StatResponse getStat() throws IOException {

        // 1. 서비스 통계치 
        StatResponse statResponse = letterStatRepository.findLetterStat(); 

        /*
            1. 서비스 통계치 겟또
            2. 호출일자를 기준으로 코로나 테이블 조회
            3. 데이터 없으면 공공api 호출하여 데이터 조회한 후 DB에 저장
               (당일 통계치 & 전날 대비 증감량)
            4. 코로나 통계치 겟또
            5. 1, 4 묶어서 리턴
        */
        
        // 2. 호출일자 기준으로 코로나 테이블 조회 
        String todayDate = new SimpleDateFormat("yyyyMMdd").format(new Date()); 
        String yesDate = new SimpleDateFormat("yyyyMMdd").format(new Date().getTime()+(1000*60*60*24*-1)); 

        List<CovidStat> covidStats = covidStatRepository.findByTwoDate(todayDate, yesDate); 

        // 3. 데이터 없으면
        if(covidStats.size() < 2){
            
            // 코로나 공공 api (예방접종률)
            String serviceKey = "SJMNyeTPF3YFcz53vYHdJit0yNSj785kS%2FRDdaEIi6VSAO098xEDGOTdYxLTZJnauGNknbs54UJb3JJZIyEKOg%3D%3D";

            StringBuilder urlBuilder = new StringBuilder("https://api.odcloud.kr/api/15077756/v1/vaccine-stat"); /*URL*/
            urlBuilder.append("?serviceKey=" + serviceKey);

            URL url = new URL(urlBuilder.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            // System.out.println("Response code: " + conn.getResponseCode());

            BufferedReader rd;
            if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
                rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            } else {
                rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
            }

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = rd.readLine()) != null) {
                sb.append(line);
            }

            rd.close();
            conn.disconnect();

        }else{
            covidStats
            .stream()
            .map(statResponse); 
        }

        return statResponse; 
    }
}