package com.nexters.covid.letter.service;

import com.nexters.covid.letter.api.dto.LetterRequest;
import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.api.dto.QuestionResponse;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.question.QuestionRepository;
import com.nexters.covid.letter.domain.sendoption.SendOptionRepository;
import com.nexters.covid.user.domain.User;
import com.nexters.covid.user.domain.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LetterService {

  private static final Long COMMON_SEND_OPTION_ID = 3L;

  private final LetterRepository letterRepository;
  private final SendOptionRepository sendOptionRepository;
  private final QuestionRepository questionRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<LetterResponse> findLettersByEmail(String email) {
    return letterRepository.findLettersByEmailOrderByCreatedDateDesc(email)
        .stream()
        .map(LetterResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<OptionResponse> findAllOptions() {
    return sendOptionRepository.findAll()
        .stream()
        .map(OptionResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<QuestionResponse> findQuestionsByOptionId(Long optionId) {
    return questionRepository
        .findQuestionsBySendOptionIdEqualsOrSendOptionIdEquals(optionId, COMMON_SEND_OPTION_ID)
        .stream()
        .map(QuestionResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public LetterResponse saveLetter(LetterRequest letterRequest) {
    User user = userRepository.findUserByEmail(letterRequest.getEmail())
        .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));

    Letter letter = letterRepository.save(new Letter(letterRequest, user));

    return new LetterResponse(letter);
  }

  @Transactional(readOnly = true)
  public LetterResponse findLetterByEncryptedId(String encryptedId) {
    Letter letter = letterRepository.findLetterByEncryptedId(encryptedId)
        .orElseThrow(() -> new IllegalArgumentException("해당 ID의 편지가 없습니다."));
    return new LetterResponse(letter);
  }
}
