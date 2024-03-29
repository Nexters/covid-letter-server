package com.nexters.covid.letter.api.dto;

import com.nexters.covid.letter.domain.Sticker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterRequest {

  private String title;

  private String contents;

//  private String letterTo;

  private Sticker sticker;

  private Long questionId;

  private Long sendOptionId;

  public Long markLetterSendOptionId() {
    if (sendOptionId == null) {
      return 7L;
    }
    return sendOptionId;
  }
}
