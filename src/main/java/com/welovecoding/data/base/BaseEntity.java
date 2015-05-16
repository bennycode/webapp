package com.welovecoding.data.base;

import com.welovecoding.data.user.entity.User;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
public abstract class BaseEntity<PK extends Serializable> extends AbstractPersistable<PK> {

  //  @NotNull
  @Size(min = 1, max = 255)
//  @Basic(optional = false)
  private String name;

  //  @NotNull
  @Size(min = 1, max = 255)
//  @Basic(optional = false)
  private String slug;
  @Temporal(value = TemporalType.TIMESTAMP)
  private Date created;

  @Temporal(value = TemporalType.TIMESTAMP)
  private Date lastModified;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private User creator;

  @ManyToOne(cascade = CascadeType.PERSIST)
  private User lastEditor;

  protected BaseEntity() {

  }

  protected BaseEntity(PK id, Date created, Date lastModified) {
    this.setId(id);
    this.created = created;
    this.lastModified = lastModified;
  }

  public BaseEntity(PK id, String name, String slug, Date created, Date lastModified, User creator, User lastEditor) {
    this.setId(id);
    this.name = name;
    this.slug = slug;
    this.created = created;
    this.lastModified = lastModified;
    this.creator = creator;
    this.lastEditor = lastEditor;
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  protected void setCreated(Date created) {
    this.created = created;
  }

  protected void setLastModified(Date lastModified) {
    this.lastModified = lastModified;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.slug = Slugify.slugify(name);
    this.name = name;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  public User getCreator() {
    return creator;
  }

  public void setCreator(User creator) {
    this.creator = creator;
  }

  public User getLastEditor() {
    return lastEditor;
  }

  public void setLastEditor(User lastEditor) {
    this.lastEditor = lastEditor;
  }

  @Override
  public int hashCode() {
    int hash = 7;
    hash = 97 * hash + Objects.hashCode(this.getId());
    return hash;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    final BaseEntity other = (BaseEntity) obj;
    return Objects.equals(this.getId(), other.getId());
  }

  @Override
  public String toString() {
    return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
  }

}
