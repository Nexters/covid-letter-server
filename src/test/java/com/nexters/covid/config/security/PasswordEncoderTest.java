package com.nexters.covid.config.security;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PasswordEncoderTest {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Test
  public void passwordEncodeTest() {
    String rawIdentifier = "identifier";
    String encodedIdentifier = passwordEncoder.encode(rawIdentifier);

    assertThat(rawIdentifier).isNotEqualTo(encodedIdentifier);
  }

  @Test
  public void passwordMatchTest() {
    String rawIdentifier = "identifier";
    String encodedIdentifier = passwordEncoder.encode(rawIdentifier);

    assertThat(passwordEncoder.matches(rawIdentifier, encodedIdentifier)).isTrue();
  }
}
