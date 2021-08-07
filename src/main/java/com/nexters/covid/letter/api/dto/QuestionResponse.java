package com.nexters.covid.letter.api.dto;

import static org.springframework.beans.BeanUtils.copyProperties;

import com.nexters.covid.base.Constant;
import com.nexters.covid.letter.domain.question.Question;
import com.nexters.covid.letter.domain.sendoption.SendOption;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResponse {

  private Long id;
  private String text;
  private boolean commonOptionId;

  public QuestionResponse(Question source) {
    copyProperties(source, this);
    this.commonOptionId = isCommonOptionId(source.getSendOption());
  }

  private boolean isCommonOptionId(SendOption sendOption) {
    return sendOption.getId().equals(Constant.COMMON_SEND_OPTION_ID);
  }
}
