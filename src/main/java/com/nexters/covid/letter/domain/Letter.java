package com.nexters.covid.letter.domain;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexters.covid.base.BaseEntity;
import com.nexters.covid.letter.api.dto.LetterRequest;
import com.nexters.covid.user.domain.User;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
public class Letter extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JsonIgnore
  @JoinColumn(name = "user_id")
  private User user;

  private String letterTo;

  private String title;

  private String contents;

  private String email;

  @Enumerated(EnumType.STRING)
  private State state;

  @Enumerated(EnumType.STRING)
  private Sticker sticker;

  private Long questionId;

  private Long sendOptionId;

  private String encryptedId;

  public Letter(LetterRequest request, User user) {
    this.user = user;
    this.letterTo = request.getEmail();
    this.title = request.getTitle();
    this.contents = encodeContents(request.getContents());
    this.email = user.getEmail();
    this.state = State.PENDING;
    this.sticker = request.getSticker();
    this.questionId = request.getQuestionId();
    this.sendOptionId = markLetterSendOptionId(request.getSendOptionId());
    this.encryptedId = generateEncryptedId();
  }

  private String generateEncryptedId() {
    return UUID.randomUUID().toString();
  }

  private String encodeContents(String contents) {
    return encodeBase64String(contents.getBytes());
  }

  public void updateLetterState() {
    if (this.state == State.SEND) {
      this.state = State.DISPLAYED;
    }
  }

  public String decodeContents() {
    return new String(decodeBase64(this.contents));
  }

  public Long markLetterSendOptionId(Long sendOptionId) {
    if (sendOptionId == null) {
      return 7L;
    }
    return sendOptionId;
  }

  public void updateLetterSendOption(Long sendOptionId) {
    if (this.sendOptionId != 7) {
      throw new RuntimeException("발송 옵션이 이미 설정되어 있습니다.");
    }
    this.sendOptionId = sendOptionId;
  }
}
