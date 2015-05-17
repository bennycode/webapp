package com.welovecoding.data.playlist;


import com.welovecoding.data.author.Author;
import com.welovecoding.data.base.BaseEntity;
import com.welovecoding.data.category.Category;
import com.welovecoding.data.user.entity.User;
import com.welovecoding.data.video.Video;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Playlist extends BaseEntity<Long> {


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

  public Playlist() {
    this.enabled = true;
    this.videos = new HashSet<>();
    this.provider = "YOUTUBE";
  }

  Playlist(Long id, String name, String slug, Date created, Date lastModified, User creator, User lastEditor, String provider, Category category, Author author, Set<Video> videos, String code, String description, boolean enabled) {
    super(id, name, slug, created, lastModified, creator, lastEditor);
    this.provider = provider;
    this.category = category;
    this.author = author;
    this.videos = videos;
    this.code = code;
    this.description = description;
    this.enabled = enabled;
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

  @Override
  public void setName(String name) {
    super.setName(name);
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
