package com.welovecoding.api.v1.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welovecoding.api.v1.base.BaseDTO;
import com.welovecoding.api.v1.news.NewsDTO;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AccountDTO extends BaseDTO<Long> {

  @JsonIgnore
  private Set<NewsDTO> news = new HashSet<>();

  private final String username;

  AccountDTO(Long id, Date created, Date lastModified, Set<NewsDTO> news, String username) {
    super(id, created, lastModified);
    this.news = news;
    this.username = username;
  }

  AccountDTO(AccountDTO dto, Set<NewsDTO> news, String username) {
    super(dto);
    this.news = news;
    this.username = username;
  }

  public Set<NewsDTO> getNews() {
    return news;
  }

  public String getUsername() {
    return username;
  }

}
