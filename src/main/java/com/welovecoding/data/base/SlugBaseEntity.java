package com.welovecoding.data.base;

import javax.persistence.Basic;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
public abstract class SlugBaseEntity<PK extends Serializable & Comparable> extends BaseEntity<PK> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String slug;

  protected SlugBaseEntity() {
  }

  protected SlugBaseEntity(PK id, Date created, Date lastModified, String slug) {
    super(id, created, lastModified);
    this.slug = slug;
  }

  protected abstract String getSlugableName();

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }

  @PrePersist
  public void prePersist() {
    if (slug == null) {
      this.slug = Slugify.slugify(getSlugableName());
    }
  }

}
