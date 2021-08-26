package com.nexters.covid.letter.service;

import com.nexters.covid.base.Constant;
import com.nexters.covid.base.LetterText;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.State;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MailService {

  private final JavaMailSender javaMailSender;
  private final LetterRepository letterRepository;

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
      throws MessagingException, UnsupportedEncodingException {
    List<Letter> letters = findLettersByOptionIdAndState(sendOptionId);
    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
    LetterText text = new LetterText(letterUrl, stickerUrl);

    for (Letter letter : letters) {
      msgHelper.setFrom(letter.getEmail(), "안녕, 나야");
      msgHelper.setTo(letter.getLetterTo());
      msgHelper.setSubject(generateLetterTitle(letter.getCreatedDate()));
      msgHelper.setText(text.generateLetterBody(letter), true);

      javaMailSender.send(msg);
      letterRepository.updateLetterState(letter.getEncryptedId());
    }
  }

  private String generateLetterTitle(LocalDateTime createdDate) {
    return createdDate.format(DateTimeFormatter.ofPattern(
        Constant.LETTER_TITLE_FORMAT));
  }
}
