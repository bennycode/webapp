package com.welovecoding.data.author;

import com.welovecoding.data.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;
import java.util.Date;


@Entity
public class Author extends BaseEntity<Long> {

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;
  @Size(min = 0, max = 255)
  private String website;
  @Size(min = 0, max = 255)
  private String channelUrl;

  public Author() {
    this.description = "";
    this.website = "";
    this.channelUrl = "";
  }

  public Author(String name) {
    this();
    this.setName(name);
  }

  Author(Long id, Date created, Date lastModified, String description, String website, String channelUrl) {
    super(id, created, lastModified);
    this.description = description;
    this.website = website;
    this.channelUrl = channelUrl;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    this.website = website;
  }

  public String getChannelUrl() {
    return channelUrl;
  }

  public void setChannelUrl(String channelUrl) {
    this.channelUrl = channelUrl;
  }

  @Override
  public String toString() {
    return super.toString();
  }
}
