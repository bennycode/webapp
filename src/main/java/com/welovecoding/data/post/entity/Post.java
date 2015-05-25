package com.welovecoding.data.post.entity;

import com.welovecoding.data.base.SlugBaseEntity;
import org.hibernate.annotations.AnyMetaDef;
import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.MetaValue;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Post extends SlugBaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String title;

  @Lob
  private String description;

  @ManyToAny(metaColumn = @Column(name = "element_type"))
  @AnyMetaDef(
    idType = "integer",
    metaType = "string",
    metaValues = {
      @MetaValue(value = "TextPostElement", targetEntity = TextPostElement.class),
      @MetaValue(value = "VideoPostElement", targetEntity = VideoPostElement.class)
    })
  @JoinTable(
    name = "obj_properties",
    joinColumns =
    @JoinColumn(
      name = "obj_id"),
    inverseJoinColumns =
    @JoinColumn(
      name = "property_id"
    )
  )
  private List<PostElement> elements = new ArrayList<>();

  Post(Long id, Date created, Date lastModified, String slug, String title, String description, List<PostElement> elements) {
    super(id, created, lastModified, slug);
    this.title = title;
    this.description = description;
    this.elements = elements;
  }

  @Override
  protected String getSlugableName() {
    return getTitle();
  }

  @Override
  public Comparable comparableAttribute() {
    return getTitle();
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


  public String getDescription() {
    return description;
  }


  public void setDescription(String description) {
    if (description != null && description.length() > 1024) {
      description = description.substring(0, 1024);
    }

    this.description = description;
  }


}
