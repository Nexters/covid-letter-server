package com.nexters.covid.stat.service;

import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.State;
import com.nexters.covid.stat.api.dto.CovidStatResponse;
import com.nexters.covid.stat.domain.CovidStat;
import com.nexters.covid.stat.domain.CovidStatRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CovidStatService {

  @Value("${api.secret.key}")
  private String serviceKey;

  private final CovidStatRepository covidStatRepository;
  private final LetterRepository letterRepository;

  public CovidStatResponse readTodayStat() {
    List<CovidStat> stat = findCovidStat();
    long lettersCount = letterRepository.count();
    long lettersPending = letterRepository.countAllByState(State.PENDING);

    return new CovidStatResponse(stat.get(0), stat.get(1), lettersCount, lettersPending);
  }

  public List<CovidStat> findCovidStat() {
    Date today = new Date();
    SimpleDateFormat dateFormatCured = new SimpleDateFormat("yyyy-MM-dd");
    String date = dateFormatCured.format(today);

    CovidStat todayStat = covidStatRepository.findByDate(date);

    if (ObjectUtils.isEmpty(todayStat)) {
      try {
        // 조회해서 저장
        saveStat();
      } catch (RuntimeException | ParseException | ParserConfigurationException | IOException | SAXException e) {
        return covidStatRepository.findTop2ByOrderByIdDesc();
      }
    }
    return covidStatRepository.findTop2ByOrderByIdDesc();
  }

  @Transactional
  public void saveStat()
      throws ParseException, ParserConfigurationException, IOException, SAXException {

    Date today = new Date();
    Date yesterday = new Date(today.getTime() + (1000 * 60 * 60 * 24 * -1));

    SimpleDateFormat dateFormatCured = new SimpleDateFormat("yyyyMMdd");
    SimpleDateFormat dateFormatVaccinated = new SimpleDateFormat("yyyy-MM-dd");

    // 감염 현황 api ------------------------------------------------------------------------------------------------
    String apiUrlCured =
        "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson?ServiceKey="
            + serviceKey + "&startCreateDt=" + dateFormatCured.format(yesterday) + "&endCreateDt="
            + dateFormatCured.format(today);
    String resultCuredToday = "";
    String resultConfirmedToday = "";
    String resultCuredYes = "";
    String resultConfirmedYes = "";

    DocumentBuilderFactory dbFactoty = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder = dbFactoty.newDocumentBuilder();
    Document doc = dBuilder.parse(apiUrlCured);

    doc.getDocumentElement().normalize();
    NodeList nList = doc.getElementsByTagName("item");

    for (int temp = 0; temp < nList.getLength(); temp++) {
      Node nNode = nList.item(temp);
      if (nNode.getNodeType() == Node.ELEMENT_NODE) {

        Element eElement = (Element) nNode;
        if (temp == 0) {
          resultConfirmedToday = getTagValue("decideCnt", eElement);
          resultCuredToday = getTagValue("clearCnt", eElement);
        } else {
          resultConfirmedYes = getTagValue("decideCnt", eElement);
          resultCuredYes = getTagValue("clearCnt", eElement);
        }
      }
    }

    // 백신 접종률 api ------------------------------------------------------------------------------------------------
    String apiUrlVaccinated =
        "https://api.odcloud.kr/api/15077756/v1/vaccine-stat?page=1&perPage=1&cond%5BbaseDate%3A%3AEQ%5D="
            + dateFormatVaccinated.format(today) + "%2000%3A00%3A00&serviceKey=" + serviceKey;
    String resultVaccinated = "";

    try {
      URL urlVaccinated = new URL(apiUrlVaccinated);
      BufferedReader bf;

      bf = new BufferedReader(new InputStreamReader(urlVaccinated.openStream(), "UTF-8"));
      resultVaccinated = bf.readLine();
    } catch (Exception e) {
      e.printStackTrace();
    }

    JSONParser jsonParser = new JSONParser();
    JSONObject jsonObjectVaccinated = (JSONObject) jsonParser.parse(resultVaccinated);
    JSONArray jsonArrayVaccinated = (JSONArray) jsonObjectVaccinated.get("data");

    if (jsonArrayVaccinated.size() == 0) {
      throw new RuntimeException();
    }

    JSONObject vaccinatedJson = (JSONObject) jsonArrayVaccinated.get(0);
    String todayVaccinated = String
        .format("%.1f", (Long) vaccinatedJson.get("totalSecondCnt") / 51353920.0 * 100);
    String yesVaccinated = String.format("%.1f",
        ((Long) vaccinatedJson.get("totalSecondCnt") - (Long) vaccinatedJson
            .get("accumulatedSecondCnt")) / 51353920.0 * 100);

    // DB에 저장할 Object ------------------------------------------------------------------------------------------------

    CovidStat todayStat = new CovidStat();

    todayStat.setDate(dateFormatVaccinated.format(today));
    todayStat.setConfirmed(resultConfirmedToday);
    todayStat.setCured(resultCuredToday);
    todayStat.setVaccinated(todayVaccinated);
    covidStatRepository.save(todayStat);
  }

  // Util 함수
  private String getTagValue(String tag, Element eElement) {
    NodeList nlList = eElement.getElementsByTagName(tag).item(0).getChildNodes();
    Node nValue = nlList.item(0);
    if (nValue == null) {
      return null;
    }
    return nValue.getNodeValue();
  }
}
