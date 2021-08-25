package com.nexters.covid.user.service;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.user.api.dto.LoginRequest;
import com.nexters.covid.user.api.dto.LoginResponse;
import com.nexters.covid.user.api.dto.UserResponse;
import com.nexters.covid.user.domain.User;
import com.nexters.covid.user.domain.UserRepository;
import com.nexters.covid.config.security.JwtTokenProvider;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;
  private final LetterRepository letterRepository;
  private final JwtTokenProvider jwtTokenProvider;

  @Transactional
  public Optional<User> findByIdentifier(LoginRequest loginRequest) {
    return userRepository.findUserByIdentifier(loginRequest.getIdentifier());
  }

  @Transactional
  public User findUser(LoginRequest loginRequest) {
    return findByIdentifier(loginRequest)
        .map(user -> {
          user.updateLastLoginTime();
          return user;
        }).orElseGet(() -> userRepository.save(new User(loginRequest)));
  }

  @Transactional
  public LoginResponse login(LoginRequest loginRequest) {
    return new LoginResponse(jwtTokenProvider.createToken(findUser(loginRequest)));
  }

  @Transactional(readOnly = true)
  public UserResponse findUserEmail(String email) {
    // TODO: 예외처리,,근데 사실 앞단에서 해주는데 고민 필요
    User user = userRepository.findUserByEmail(email)
        .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
    List<Letter> letters = letterRepository.findLettersByEmailOrderByCreatedDateDesc(email);

    return new UserResponse(user, letters.size());
  }
}
