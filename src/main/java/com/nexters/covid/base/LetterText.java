package com.nexters.covid.base;

import com.nexters.covid.letter.domain.Letter;
import com.nexters.covid.letter.domain.Sticker;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LetterText {

  private String letterUrl;

  private String stickerUrl;

  public LetterText(String letterUrl, String stickerUrl) {
    this.letterUrl = letterUrl;
    this.stickerUrl = stickerUrl;
  }

  public String generateLetterBody(Letter letter) {
    StringBuilder sb = new StringBuilder();
    sb.append("<html><head>");
    sb.append("<style>\n"
        + "            html, body {\n"
        + "                padding: 0;\n"
        + "                margin: 0;\n"
        + "            }\n"
        + "            a:hover {\n"
        + "                cursor: pointer;\n"
        + "            }\n"
        + "        </style>\n"
        + "    </head>");
    sb.append("<body>\n"
        + "        <div style=\"margin: 0 auto; font-family: 'Apple SD Gothic Neo',arial,sans-serif; font-style: normal; padding: 0; letter-spacing: -0.015em;\">\n"
        + "            <div style=\"background-color: #FBF8ED;\">\n"
        + "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" style=\"text-align: center; padding: 60px 0; margin-left: auto;margin-right: auto;width: 100%; max-width: 420px;\">\n"
        + "                    <tbody>\n"
        + "                        <tr>\n"
        + "                            <td>\n"
        + "                                <!-- 이미지 -->\n"
        + "                                <img src=\"");
    sb.append(stickerUrl(letter.getSticker()) + "\" style=\"margin-bottom: 16px;\" />\n"
        + "                                <div style=\"font-weight: 200;font-size: 20px;line-height: 32px;color: #22211F; padding: 0 0 32px;\">\n"
        + "                                    안녕, 나야.\n"
        + "                                    <br />\n"
        + "                                    오랜만이지?\n"
        + "                                </div>\n"
        + "                            </td>\n"
        + "                        </tr>\n"
        + "                        <tr>\n"
        + "                            <td style=\"padding: 8px; font-size: 12px; color: #5B5A57; line-height: 19px; text-align: left; border-top: 2px dashed #ded9c6; border-bottom: 2px dashed #ded9c6;\">\n"
        + "                                <dl style=\"display: inherit; padding-right: 24px; border-right: 1px solid #BBBBBB;\">\n"
        + "                                    <dt style=\"display: inherit;\">TO</dt>\n"
        + "                                    <dd style=\"display: inherit; padding: 0 4px;\">:</dd>\n"
        + "                                    <!-- 수신인 -->\n"
        + "                                    <dd style=\"display: inherit; \">ME</dd>\n"
        + "                                </dl>\n"
        + "                                <dl style=\"display: inherit; padding-left: 24px;\">\n"
        + "                                    <dt style=\"display: inherit;\">FROM</dt>\n"
        + "                                    <dd style=\"display: inherit; padding: 0 4px;\">:</dd>\n"
        + "                                    <!-- 발신인 -->\n"
        + "                                    <dd style=\"display: inherit;\">ME</dd>\n"
        + "                                </dl>\n"
        + "                            </td>\n"
        + "                        </tr>\n"
        + "                        <tr>\n"
        + "                            <td style=\"padding: 16px 0 24px; text-align: center;\">\n"
        + "                                <div style=\"background-color: #F6F4E8; padding: 24px 0; font-size: 12px; line-height: 19px; color: #5B5A57;\">\n"
        + "                                    <!-- 날짜 -->\n"
        + "                                    이 편지는 ");
    sb.append(formattedDateTime(letter.getCreatedDate()) + "에\n"
        + "                                    <br />\n"
        + "                                    내가 적은 편지야.\n"
        + "                                    <br />\n"
        + "                                    <br />\n"
        + "                                    나를 잊어버렸을 수도 있고, 나를 기다려왔을 수도 있겠네.\n"
        + "                                    <br />\n"
        + "                                    <br />\n"
        + "                                    <!-- 기간 -->\n"
        + "                                    지금으로부터 ");
    sb.append(durationOfDays(letter.getCreatedDate()) + "일 전의 나는\n"
        + "                                    <br />\n"
        + "                                    어떤 생각을 가지고 있었을까?\n"
        + "                                    <br />\n"
        + "                                    <br />\n"
        + "                                    한번 읽어보지 않을래?\n"
        + "                                </div>\n"
        + "                            </td>\n"
        + "                        </tr>\n"
        + "                        <tr>\n"
        + "                            <td style=\"padding: 0 0 16px;\">\n"
        + "                                <!-- 링크 -->\n"
        + "                                <a href=\"");
    sb.append(directLetterUrl(letter.getEncryptedId())
        + "\" target=\"__blank\" style=\"font-weight: bold; font-size: 16px; line-height: 22px;padding: 16px 0; border-radius: 4px;width: 100%; display: block; text-decoration: none; background: #11373E; color: #fff; border: 0;\">편지보러가기</a>\n"
        + "                                <a href=\"https://halo-its.me\" target=\"__blank\" style=\"font-weight: bold; font-size: 16px; line-height: 22px;padding: 16px 0; border-radius: 4px;width: 100%;display: block; text-decoration: none; margin-top: 16px; border: 2px solid #11373E; background-color: transparent; color: #11373E; box-sizing: border-box;\">안녕, 나야 둘러보기</a>\n"
        + "                            </td>\n"
        + "                        </tr>\n"
        + "                        <tr>\n"
        + "                            <td style=\"text-align: center; font-weight: 300; font-size: 12px; line-height: 19px; color: #333333;\">\n"
        + "                                2021 안녕, 나야. All Rights Reserved.\n"
        + "                            </td>\n"
        + "                        </tr>\n"
        + "                    </tbody>\n"
        + "                </table>\n"
        + "            </div>\n"
        + "        </div>\n"
        + "    </body>\n"
        + "</html>");

    return sb.toString();
  }

  private String directLetterUrl(String encryptedId) {
    return letterUrl + encryptedId;
  }

  public String stickerUrl(Sticker sticker) {
    return stickerUrl + sticker.fileName();
  }

  private String formattedDateTime(LocalDateTime createdDate) {
    return createdDate.format(DateTimeFormatter.ofPattern(Constant.LETTER_DATE_FORMAT));
  }

  private Long durationOfDays(LocalDateTime createdDate) {
    return Duration.between(createdDate, LocalDateTime.now()).toDays();
  }
}
