package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.State;
import com.nexters.covid.letter.domain.Sticker;
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

  private String encryptedId;

  public LetterResponse(Letter source) {
    copyProperties(source, this);
  }
}
