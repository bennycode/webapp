package com.welovecoding.data.post.entity;

import com.welovecoding.data.tutorial.entity.Provider;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class VideoPostElement extends PostElement {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String title;

  @Column(unique = true)
  private String code;

  @Embedded
  @Enumerated(EnumType.STRING)
  private Provider provider;

  @Size(min = 0, max = 1024)
  @Column(length = 1024)
  private String description;

  private String previewImageUrl;

  private String downloadUrl;

  private String permalink;

  public VideoPostElement() {
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
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

  public String getPreviewImageUrl() {
    return previewImageUrl;
  }

  public void setPreviewImageUrl(String previewImageUrl) {
    this.previewImageUrl = previewImageUrl;
  }

  public String getDownloadUrl() {
    return downloadUrl;
  }

  public void setDownloadUrl(String downloadUrl) {
    this.downloadUrl = downloadUrl;
  }

  public String getPermalink() {
    return permalink;
  }

  public void setPermalink(String permalink) {
    this.permalink = permalink;
  }

  public Provider getProvider() {
    return provider;
  }

  public void setProvider(Provider provider) {
    this.provider = provider;
  }
}
