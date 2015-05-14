package com.welovecoding.data.news;


import com.welovecoding.data.account.Account;
import com.welovecoding.data.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class News extends BaseEntity<Long> {

  private static final long serialVersionUID = -2952735933715107252L;

  @ManyToOne//(fetch = FetchType.LAZY)
//    @NotNull(message = "The account of the creator must be set.")
  private Account account;
  @NotNull(message = "Failed to generate slug.")
  @Column(unique = true)
  private String slug;
  @NotNull(message = "The title must be set.")
  @Size(min = 1, max = 1024,
    message = "The title '${validatedValue}' must be between {min} and {max} characters long.")
  private String title;
  @NotNull
  @Size(min = 1, max = Integer.MAX_VALUE,
    message = "The text must be between {min} and {max} characters long.")
  @Lob
  private String text;
  @NotNull
  @Size(min = 1, max = 1024,
    message = "The description must be between {min} and {max} characters long.")
  @Column(length = 1024)
  private String description;

  public News() {
  }

  public News(Account account, String slug, String title, String description, String text) {
    this.account = account;
    this.slug = slug;
    this.title = title;
    this.text = text;
    this.description = description;
  }

  News(Long id, Date created, Date lastModified, Account account, String slug, String title, String description, String text) {
    super(id, created, lastModified);
    this.account = account;
    this.slug = slug;
    this.title = title;
    this.text = text;
    this.description = description;
  }

  public Account getAccount() {
    return account;
  }

  public String getText() {
    return text;
  }

  public String getDescription() {
    return description;
  }

  public String getTitle() {
    return title;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

}
