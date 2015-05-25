package com.welovecoding.api.v1.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.hateoas.ResourceSupport;

import java.io.Serializable;
import java.util.Date;

public abstract class BaseDTO<PK extends Serializable> extends ResourceSupport {

  @JsonIgnore
  private PK identifier;
  private Date created;
  private Date lastModified;

  protected BaseDTO() {
  }

  protected BaseDTO(PK identifier, Date created, Date lastModified) {
    this.identifier = identifier;
    this.created = created;
    this.lastModified = lastModified;
  }

  protected BaseDTO(BaseDTO<PK> dto) {
    this.identifier = dto.getIdentifier();
    this.created = dto.getCreated();
    this.lastModified = dto.getLastModified();
  }

  public Date getCreated() {
    return created;
  }

  public Date getLastModified() {
    return lastModified;
  }

  public PK getIdentifier() {
    return identifier;
  }

}
