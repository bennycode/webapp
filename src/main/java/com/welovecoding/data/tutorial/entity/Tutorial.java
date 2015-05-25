package com.welovecoding.data.tutorial.entity;


import com.welovecoding.data.base.SlugBaseEntity;
import com.welovecoding.data.category.entity.Category;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
public class Tutorial extends SlugBaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String title;

  @Lob
  @NotNull
  private String description;

  private boolean enabled;

  @NotEmpty
  @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  private List<Author> authors;

  @ManyToOne
  private Category category;

  @Embedded
  @Enumerated(EnumType.STRING)
  private LanguageCode languageCode;

  @Embedded
  @Enumerated(EnumType.STRING)
  private Difficulty difficulty;


  Tutorial(Long id, Date created, Date lastModified, String slug, String title, String description, boolean enabled, List<Author> authors, Category category, LanguageCode languageCode, Difficulty difficulty) {
    super(id, created, lastModified, slug);
    this.title = title;
    this.description = description;
    this.enabled = enabled;
    this.authors = authors;
    this.category = category;
    this.languageCode = languageCode;
    this.difficulty = difficulty;
  }

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

  public LanguageCode getLanguageCode() {
    return languageCode;
  }

  public void setLanguageCode(LanguageCode languageCode) {
    this.languageCode = languageCode;
  }

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

  public Difficulty getDifficulty() {
    return difficulty;
  }

  public void setDifficulty(Difficulty difficulty) {
    this.difficulty = difficulty;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Author> authors) {
    this.authors = authors;
  }
}
