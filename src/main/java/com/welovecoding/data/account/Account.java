package com.welovecoding.data.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.welovecoding.data.base.BaseEntity;
import com.welovecoding.data.news.News;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account extends BaseEntity<Long> {

  private static final long serialVersionUID = -2952735933715107252L;

  @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
  private Set<News> news = new HashSet<>();

  @JsonIgnore
  @NotNull(message = "The password must be set.")
  @Size(min = 1, max = 64,
    message = "The password must be between {min} and {max} characters long.")
  private String password;
  @NotNull(message = "The username must be set.")
  @Size(min = 1, max = 64,
    message = "The username must be between {min} and {max} characters long.")
  @Column(unique = true)
  private String username;

  Account() {
    super();
  }

  Account(Long id, Date created, Date lastModified, String username, String password) {
    super(id, created, lastModified);
    this.username = username;
    this.password = password;
  }

  Account(Long id, Date created, Date lastModified, Set news, String username, String password) {
    super(id, created, lastModified);
    this.news = news;
    this.username = username;
    this.password = password;
  }

  public Set<News> getNews() {
    return news;
  }

  public String getPassword() {
    return password;
  }

  public String getUsername() {
    return username;
  }

}
