package com.welovecoding.api.v1.news;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welovecoding.api.v1.account.AccountDTO;
import com.welovecoding.api.v1.base.BaseDTO;

import java.util.Date;

public class NewsDTO extends BaseDTO<Long> {

  @JsonIgnore
  private AccountDTO account;
  private String slug;
  private String title;
  private String text;
  private String description;

  public NewsDTO() {

  }

  NewsDTO(Long id, Date created, Date lastModified, AccountDTO account, String slug, String title, String description, String text) {
    super(id, created, lastModified);
    this.account = account;
    this.slug = slug;
    this.title = title;
    this.text = text;
    this.description = description;
  }

  NewsDTO(NewsDTO dto, AccountDTO account, String slug, String title, String description, String text) {
    super(dto);
    this.account = account;
    this.slug = slug;
    this.title = title;
    this.text = text;
    this.description = description;
  }

  public AccountDTO getAccount() {
    return account;
  }

  public String getSlug() {
    return slug;
  }

  public String getTitle() {
    return title;
  }

  public String getText() {
    return text;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public String toString() {
    return "NewsDTO{" + "account=" + account + ", slug=" + slug + ", title=" + title + ", text=" + text + ", description=" + description + '}';
  }

}
