package com.nexters.covid.letter.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.service.LetterService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class LetterController {

  private final LetterService letterService;

  @GetMapping("/letter/option")
  public BaseResponse<List<OptionResponse>> options() {
    List<OptionResponse> options = letterService.options();
    return new BaseResponse<>(200, 0, "", options);
  }
}
