package com.nexters.covid.letter.service;

import com.nexters.covid.base.Constant;
import com.nexters.covid.letter.api.dto.LetterRequest;
import com.nexters.covid.letter.api.dto.LetterResponse;
import com.nexters.covid.letter.api.dto.OptionResponse;
import com.nexters.covid.letter.api.dto.QuestionResponse;
import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.LetterRepository;
import com.nexters.covid.letter.domain.question.Question;
import com.nexters.covid.letter.domain.question.QuestionRepository;
import com.nexters.covid.letter.domain.sendoption.SendOption;
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

  private final LetterRepository letterRepository;
  private final SendOptionRepository sendOptionRepository;
  private final QuestionRepository questionRepository;
  private final UserRepository userRepository;

  @Transactional(readOnly = true)
  public List<LetterResponse> findLettersByEmail(String email, boolean unposted) {
    if (unposted) {
      return letterRepository.findLettersByEmailOrderByCreatedDateDesc(email)
          .stream()
          .filter(Letter::unpostedSendOption)
          .map(LetterResponse::new)
          .collect(Collectors.toList());
    }
    return letterRepository.findLettersByEmailOrderByCreatedDateDesc(email)
        .stream()
        .map(LetterResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public SendOption findSendOptionById(Long sendOptionId) {
    return sendOptionRepository.findSendOptionById(sendOptionId)
        .orElseThrow(() -> new IllegalArgumentException("발송 옵션이 존재하지 않습니다."));
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
    return questionRepository.findQuestionsBySendOptionIdEqualsOrSendOptionIdEquals(optionId,
        Constant.COMMON_SEND_OPTION_ID)
        .stream()
        .map(QuestionResponse::new)
        .collect(Collectors.toList());
  }

  @Transactional
  public LetterResponse saveLetter(LetterRequest letterRequest, String email) {
    User user = userRepository.findUserByEmail(email)
        .orElseThrow(() -> new RuntimeException("사용자가 없습니다."));
    SendOption option = findSendOptionById(letterRequest.markLetterSendOptionId());

    Letter letter = letterRepository.save(new Letter(letterRequest, user, option));

    return new LetterResponse(letter);
  }

  @Transactional(readOnly = true)
  public LetterResponse findLetterByEncryptedId(String encryptedId) {
    Letter letter = letterRepository.findLetterByEncryptedId(encryptedId)
        .orElseThrow(() -> new IllegalArgumentException("해당 ID의 편지가 없습니다."));

    Question question = questionRepository.findQuestionById(letter.getQuestionId());

    return new LetterResponse(letter, question);
  }

  @Transactional
  public LetterResponse updateLetterState(String encryptedId) {
    Letter letter = letterRepository.findLetterByEncryptedId(encryptedId)
        .map(l -> {
          l.updateLetterState();
          return l;
        }).orElseThrow(() -> new IllegalArgumentException("해당 ID의 편지가 없습니다."));
    return new LetterResponse(letter);
  }

  @Transactional
  public LetterResponse updateLetter(String encryptedId, LetterRequest letterRequest) {
    Letter letter = letterRepository.findLetterByEncryptedId(encryptedId)
        .map(l -> {
          l.updateLetterSendOption(findSendOptionById(letterRequest.getSendOptionId()));
          return l;
        }).orElseThrow(() -> new IllegalArgumentException("해당 ID의 편지가 없습니다."));

    return new LetterResponse(letter);
  }
}
