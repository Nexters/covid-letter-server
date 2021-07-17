package com.nexters.covid.config.security;

import com.nexters.covid.user.domain.User;
import com.nexters.covid.user.domain.UserRepository;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtTokenService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
    User user = userRepository.findUserByIdentifier(identifier)
        .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    return new org.springframework.security.core.userdetails.User(user.getEmail(),
        user.getIdentifier(), new ArrayList<>());
  }
}
