package com.nexters.covid.base;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
  private Integer status;
  private Integer errorCode;
  private String message;
  private T data;

  public BaseResponse<?> failed(Integer errorCode, String message) {
    return new BaseResponse<>(errorCode, message);
  }

  private BaseResponse(Integer errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }
}
