package com.nexters.covid.letter.service;

import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.SendOptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LetterService {

  private final LetterRepository letterRepository;
  private final SendOptionRepository sendOptionRepository;

  @Transactional(readOnly = true)
  public List<OptionResponse> options() {
    return sendOptionRepository.findAll()
        .stream()
        .map(OptionResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<LetterResponse> letters(String email) {
    return letterRepository.findLettersByEmail(email)
        .stream()
        .map(LetterResponse::new)
        .collect(Collectors.toList());
  }
}
