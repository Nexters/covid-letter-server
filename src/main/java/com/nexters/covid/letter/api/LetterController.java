package com.nexters.covid.letter.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.api.dto.QuestionResponse;
import com.nexters.covid.letter.service.LetterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LetterController {

  private final LetterService letterService;

  @GetMapping("/letters")
  public BaseResponse<List<LetterResponse>> findLettersByEmail(Authentication authentication) {
    List<LetterResponse> letters = letterService.findLettersByEmail(authentication.getName());
    return new BaseResponse<>(200, 0, "", letters);
  }

  @GetMapping("/letters/options")
  public BaseResponse<List<OptionResponse>> findAllOptions() {
    List<OptionResponse> options = letterService.findAllOptions();
    return new BaseResponse<>(200, 0, "", options);
  }

  @GetMapping("/letters/options/{optionId}")
  public BaseResponse<List<QuestionResponse>> findQuestionsByOptionId(@PathVariable("optionId") Long optionId) {
    List<QuestionResponse> questions = letterService.findQuestionsByOptionId(optionId);
    return new BaseResponse<>(200, 0, "", questions);
  }
}
