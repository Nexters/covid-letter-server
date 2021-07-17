package com.nexters.covid.user.service;

import com.nexters.covid.user.api.dto.LoginRequest;
import com.nexters.covid.user.api.dto.LoginResponse;
import com.nexters.covid.user.domain.User;
import com.nexters.covid.user.domain.UserRepository;
import com.nexters.covid.config.security.JwtTokenProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public Optional<User> findByIdentifier(LoginRequest loginRequest) {
    return userRepository.findUserByIdentifier(loginRequest.getIdentifier());
  }

  private User findUser(LoginRequest loginRequest) {
    return findByIdentifier(loginRequest)
        .orElseGet(() -> userRepository.save(new User(loginRequest)));
  }

  public LoginResponse login(LoginRequest loginRequest) {
    return new LoginResponse(jwtTokenProvider.createToken(findUser(loginRequest)));
  }
}
