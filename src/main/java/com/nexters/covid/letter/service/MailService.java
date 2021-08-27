package com.nexters.covid.letter.service;

import com.nexters.covid.base.LetterText;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.State;
import java.util.List;
import javax.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailService {

  private final LetterRepository letterRepository;
  private final MailAsyncService mailAsyncService;

  @Value("${prefix.letter.url}")
  private String letterUrl;

  @Value("${prefix.sticker.url}")
  private String stickerUrl;

  @Transactional(readOnly = true)
  public List<Letter> findLettersByOptionIdAndState(Long sendOptionId) {
    return letterRepository.findLettersBySendOptionIdAndState(sendOptionId, State.PENDING);
  }

  @Transactional
  public void sendLetters(Long sendOptionId)
      throws MessagingException {
    List<Letter> letters = findLettersByOptionIdAndState(sendOptionId);
    LetterText text = new LetterText(letterUrl, stickerUrl);

    for (Letter letter : letters) {
      mailAsyncService.sendLetter(letter, text);
    }
  }
}
