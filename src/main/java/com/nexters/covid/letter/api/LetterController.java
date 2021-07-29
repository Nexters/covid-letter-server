package com.nexters.covid.letter.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.service.LetterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LetterController {

  private final LetterService letterService;

  @GetMapping("/letters/options")
  public BaseResponse<List<OptionResponse>> options() {
    List<OptionResponse> options = letterService.options();
    return new BaseResponse<>(200, 0, "", options);
  }

  @GetMapping("/letters")
  public BaseResponse<List<LetterResponse>> letters(Authentication authentication) {
    List<LetterResponse> letters = letterService.letters(authentication.getName());;
    return new BaseResponse<>(200, 0, "", letters);
  }
}
