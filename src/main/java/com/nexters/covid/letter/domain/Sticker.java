package com.nexters.covid.letter.domain;

public enum Sticker {
  HAPPY("happy.png")
  , EXPECT("expect.png")
  , SHY("shy.png")
  , LOVE("love.png")
  , UNHAPPY("unhappy.png")
  , SAD("sad.png")
  , SHOCK("shock.png")
  , BLUE("blue.png")
  , SHAME("shame.png")
  , FIGHTING("fighting.png")
  , GOOD("good.png")
  , OK("ok.png");

  private String fileName;

  Sticker(String fileName) {
    this.fileName = fileName;
  }

  public String fileName() {
    return fileName;
  }
}
