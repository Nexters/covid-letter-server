package com.nexters.covid.letter.service;

import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.domain.SendOptionRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LetterService {

  private final SendOptionRepository sendOptionRepository;

  public List<OptionResponse> options() {
    return sendOptionRepository.findAllJoinFetch()
        .stream()
        .map(OptionResponse::new)
        .collect(Collectors.toList());
  }
}
