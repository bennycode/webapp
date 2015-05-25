package com.welovecoding.data.tutorial.entity;


/**
 * Example: "EN" (English), "ja" (Japanese), "kok" (Konkani)
 * <p/>
 * http://en.wikipedia.org/wiki/List_of_ISO_639-1_codes
 * http://docs.oracle.com/javase/7/docs/api/java/util/Locale.html
 */
public enum LanguageCode {

  DE("de"),
  EN("en");

  private final String languageCode;

  private LanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  @Override
  public String toString() {
    switch (this) {
      case DE:
        return "German";
      default:
        return "English";
    }
  }

}
