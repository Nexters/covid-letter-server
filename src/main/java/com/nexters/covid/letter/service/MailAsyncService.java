package com.nexters.covid.letter.service;

import com.nexters.covid.base.Constant;
import com.nexters.covid.base.LetterText;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MailAsyncService {

  private final JavaMailSender javaMailSender;
  private final LetterRepository letterRepository;

  @Async("threadPoolTaskExecutor")
  @Transactional
  public CompletableFuture<Boolean> sendLetter(Letter letter, LetterText text)
      throws MessagingException {

    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper msgHelper = new MimeMessageHelper(msg, true, "UTF-8");
    try {
      msgHelper.setFrom("help.halo.its.me@gmail.com", "안녕, 나야");
      msgHelper.setTo(letter.getLetterTo());
      msgHelper.setSubject(generateLetterTitle(letter.getCreatedDate()));
      msgHelper.setText(text.generateLetterBody(letter), true);

      javaMailSender.send(msg);
    } catch (Exception e) {
      return CompletableFuture.completedFuture(Boolean.FALSE);
    }
    letterRepository.updateLetterState(letter.getEncryptedId());
    return CompletableFuture.completedFuture(Boolean.TRUE);
  }

  private String generateLetterTitle(LocalDateTime createdDate) {
    return createdDate.format(DateTimeFormatter.ofPattern(
        Constant.LETTER_TITLE_FORMAT));
  }
}
