package com.nexters.covid.letter.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
public class Letters {

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Letter> letters = new ArrayList<>();

  public List<Letter> letters() {
    return Collections.unmodifiableList(letters);
  }
}
