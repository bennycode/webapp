package com.welovecoding.data.tutorial.entity;


import com.welovecoding.data.base.SlugBaseEntity;
import com.welovecoding.data.category.entity.Category;
import com.welovecoding.data.video.entity.Video;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Playlist extends SlugBaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String title;

//  @Embedded
//  @Enumerated(EnumType.STRING)
//  private Provider provider;

//  @Embedded
//  @Enumerated(EnumType.STRING)
//  private Difficulty difficulty;

  @NotNull
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Category category;

  @NotNull
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private Author author;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "playlist", orphanRemoval = true)
  @OrderColumn(name = "ORDERING")
  @Valid
  private Set<Video> videos;

  private String code;

//  @Embedded
//  @Enumerated(EnumType.STRING)
//  private LanguageCode languageCode;

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;

  private String difficulty;
  private String provider;
  private String languageCode;

  private boolean enabled;


  @Override
  protected String getSlugableName() {
    return getTitle();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(String difficulty) {
    this.difficulty = difficulty;
  }

  public String getProvider() {
    return provider;
  }

  public void setProvider(String provider) {
    this.provider = provider;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(String languageCode) {
    this.languageCode = languageCode;
  }

  public Category getCategory() {
    return category;
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Set<Video> getVideos() {
    return videos;
  }

  public void setVideos(Set<Video> videoList) {
    this.videos = videoList;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

//  public LanguageCode getLanguageCode() {
//    return languageCode;
//  }
//
//  public void setLanguageCode(LanguageCode languageCode) {
//    this.languageCode = languageCode;
//  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

//  public Difficulty getDifficulty() {
//    return difficulty;
//  }
//
//  public void setDifficulty(Difficulty difficulty) {
//    this.difficulty = difficulty;
//  }

}
