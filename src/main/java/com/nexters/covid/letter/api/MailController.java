package com.nexters.covid.letter.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.letter.service.MailService;
import java.io.UnsupportedEncodingException;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MailController {

  private final MailService mailService;

  @GetMapping("/mail/{sendOptionId}")
  public BaseResponse<?> sendLetters(@PathVariable("sendOptionId") Long sendOptionId)
      throws MessagingException, UnsupportedEncodingException {
    mailService.sendLetters(sendOptionId);
    return new BaseResponse<>(200, 0, "", null);
  }
}
