package com.nexters.covid.user.api;

import com.nexters.covid.base.BaseResponse;
import com.nexters.covid.user.api.dto.LoginRequest;
import com.nexters.covid.user.api.dto.LoginResponse;
import com.nexters.covid.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping("/login")
  public BaseResponse login(LoginRequest loginRequest) {
    LoginResponse user = userService.login(loginRequest);
    return new BaseResponse<>(200, 0, "", user);
  }
}
