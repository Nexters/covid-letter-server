package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.apache.commons.codec.binary.Base64.decodeBase64;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.State;
import com.nexters.covid.letter.domain.Sticker;
import com.nexters.covid.letter.domain.question.Question;
import com.nexters.covid.letter.domain.sendoption.SendOption;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterResponse {

  private String title;

  private String contents;

  private String email;

  private State state;

  private Sticker sticker;

  private Long questionId;

  private String questionText;

  private String encryptedId;

  private String sendOptionText;

  private LocalDateTime createdDate;

  public LetterResponse(Letter source) {
    copyProperties(source, this);
    this.contents = decodeContents(source.getContents());
  }

  public LetterResponse(Letter source, SendOption option) {
    this(source);
    this.sendOptionText = option.getText();
  }

  public LetterResponse(Letter source, Question question) {
    this(source);
    this.questionText = question.getText();
  }

  private String decodeContents(String contents) {
    return new String(decodeBase64(contents));
  }
}
