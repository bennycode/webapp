package com.welovecoding.api.v1.category;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.welovecoding.api.v1.base.SlugBaseDTO;
import com.welovecoding.api.v1.tutorial.TutorialDTO;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonPropertyOrder({

})
public class CategoryDTO extends SlugBaseDTO<Long> {
  private String title;

  private String color;

  private List<TutorialDTO> tutorials = new ArrayList<>();

  CategoryDTO(Long identifier, Date created, Date lastModified, String slug, String title, String color, List<TutorialDTO> tutorials) {
    super(identifier, created, lastModified, slug);
    this.title = title;
    this.color = color;
    this.tutorials = tutorials;
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

  public List<TutorialDTO> getTutorials() {
    return tutorials;
  }

  public void setTutorials(List<TutorialDTO> tutorials) {
    this.tutorials = tutorials;
  }
}
