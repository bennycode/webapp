package com.welovecoding.api.v1.base;

import java.io.Serializable;
import java.util.Date;

public abstract class SlugBaseDTO<PK extends Serializable> extends BaseDTO<PK> {

  private String slug;

  protected SlugBaseDTO(PK identifier, Date created, Date lastModified, String slug) {
    super(identifier, created, lastModified);
    this.slug = slug;
  }

  public String getSlug() {
    return slug;
  }

  public void setSlug(String slug) {
    this.slug = slug;
  }
}
