package com.welovecoding.data.category.entity;

import com.welovecoding.data.base.SlugBaseEntity;
import com.welovecoding.data.tutorial.entity.Tutorial;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Category extends SlugBaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String title;

  @NotNull
  @Size(min = 1, max = 7)
  @Basic(optional = false)
  private String color;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "category")
  private List<Tutorial> tutorials = new ArrayList<>();

  public Category() {
  }


  Category(Long id, Date created, Date lastModified, String slug, String title, String color, List<Tutorial> tutorials) {
    super(id, created, lastModified, slug);
    this.title = title;
    this.color = color;
    this.tutorials = tutorials;
  }

  @Override
  public Comparable comparableAttribute() {
    return getTitle();
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

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public List<Tutorial> getTutorials() {
    return tutorials;
  }

  public void setTutorials(List<Tutorial> tutorials) {
    this.tutorials = tutorials;
  }


}
