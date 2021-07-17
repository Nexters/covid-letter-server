package com.nexters.covid.user.service;

import com.nexters.covid.user.api.dto.LoginRequest;
import com.nexters.covid.user.api.dto.LoginResponse;
import com.nexters.covid.user.domain.User;
import com.nexters.covid.user.domain.UserRepository;
import com.nexters.covid.config.security.JwtTokenProvider;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final JwtTokenProvider jwtTokenProvider;
  private final PasswordEncoder passwordEncoder;

  @Transactional
  public Optional<User> findByIdentifier(LoginRequest loginRequest) {
    return userRepository.findUserByIdentifier(loginRequest.getIdentifier());
  }

  @Transactional
  public User findUser(LoginRequest loginRequest) {
    String identifier = encodeIdentifier(loginRequest.getIdentifier());
    return findByIdentifier(loginRequest)
        .orElseGet(() -> userRepository.save(new User(loginRequest.getEmail(), identifier)));
  }

  private String encodeIdentifier(String identifier) {
    return passwordEncoder.encode(identifier);
  }

  @Transactional
  public LoginResponse login(LoginRequest loginRequest) {
    return new LoginResponse(jwtTokenProvider.createToken(findUser(loginRequest)));
  }
}
