package com.nexters.covid.letter.api.dto;

import com.nexters.covid.letter.domain.Sticker;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterRequest {

  private String title;

  private String contents;

  private String email;

  private Sticker sticker;

  private Long questionId;
}
