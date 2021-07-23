package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.user.domain.User;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LetterResponse {

  private String name;
  private String email;
  private List<Letter> letters;

  public LetterResponse(User source) {
    copyProperties(source, this);
    this.letters = source.letters();
  }
}
