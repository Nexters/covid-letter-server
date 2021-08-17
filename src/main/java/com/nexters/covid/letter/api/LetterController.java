package com.nexters.covid.letter.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.letter.api.dto.LetterRequest;
import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.api.dto.QuestionResponse;
import com.nexters.covid.letter.service.LetterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @GetMapping("/letters/options/{optionId}/questions")
  public BaseResponse<List<QuestionResponse>> findQuestionsByOptionId(
      @PathVariable("optionId") Long optionId) {
    List<QuestionResponse> questions = letterService.findQuestionsByOptionId(optionId);
    return new BaseResponse<>(200, 0, "", questions);
  }

  @PostMapping("/letters")
  public BaseResponse<LetterResponse> saveLetter(@RequestBody LetterRequest letterRequest) {
    LetterResponse letter = letterService.saveLetter(letterRequest);
    return new BaseResponse<>(200, 0, "", letter);
  }

  @GetMapping("/letters/{encryptedId}")
  public BaseResponse<LetterResponse> findLetterByEncryptedId(
      @PathVariable("encryptedId") String encryptedId) {
    LetterResponse letter = letterService.findLetterByEncryptedId(encryptedId);
    return new BaseResponse<>(200, 0, "", letter);
  }

  @PutMapping("/letters/{encryptedId}")
  public BaseResponse<LetterResponse> updateLetterState(@PathVariable("encryptedId") String encryptedId) {
    LetterResponse letter = letterService.updateLetterState(encryptedId);
    return new BaseResponse<>(200, 0, "", letter);
  }
}
