package com.nexters.covid.config.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  /*
  * 인증되지 않은 경우(비로그인) AuthenticationEntryPoint에서 AuthenticationException 발생
  * 해당 예외 발생시 이곳에서 처리
  * */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException authException) throws IOException, ServletException {
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    response.setContentType("application/json;charset=UTF-8");

    JSONObject json = new JSONObject();
    json.put("errorCode", HttpServletResponse.SC_UNAUTHORIZED);
    json.put("message", authException.getMessage());
    response.getWriter().print(json);
  }
}
