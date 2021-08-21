package com.nexters.covid.letter.domain;

import static org.apache.commons.codec.binary.Base64.decodeBase64;
import static org.apache.commons.codec.binary.Base64.encodeBase64String;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nexters.covid.base.BaseEntity;
import com.nexters.covid.letter.api.dto.LetterRequest;
import com.nexters.covid.letter.domain.sendoption.SendOption;
import com.nexters.covid.user.domain.User;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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

  @OneToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "send_option_id")
  private SendOption sendOption;

  private String encryptedId;

  public Letter(LetterRequest request, User user, SendOption sendOption) {
    this.user = user;
    this.letterTo = user.getEmail();
    this.title = request.getTitle();
    this.contents = encodeContents(request.getContents());
    this.email = user.getEmail();
    this.state = State.PENDING;
    this.sticker = request.getSticker();
    this.questionId = request.getQuestionId();
    this.sendOption = sendOption;
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

  public void updateLetterSendOption(SendOption sendOption) {
    this.sendOption = sendOption;
  }

  public boolean unpostedSendOption() {
    return sendOption.isUnpostedLetter();
  }
}
