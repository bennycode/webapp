package com.welovecoding.api.v1.playlist;

import com.welovecoding.api.v1.video.StatusDTO;
import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
  "id",
  "name",
  "language",
  "categoryName",
  "providerName",
  "numberOfVideos",
  "description",
  "owner",
  "status",
  "difficulty"
})
public class PlaylistDTO {

  private Long id;
  private String name;
  private String language;
  private String categoryName;
  private String providerName;
  private int numberOfVideos;
  private String description;
  private AuthorDTO owner;
  private StatusDTO status;
//  private String difficulty;

  public PlaylistDTO() {
  }

  PlaylistDTO(Long id, String name, String language, String categoryName, String providerName, int numberOfVideos, String description, AuthorDTO owner, StatusDTO status) {
    this.id = id;
    this.name = name;
    this.language = language;
    this.categoryName = categoryName;
    this.providerName = providerName;
    this.numberOfVideos = numberOfVideos;
    this.description = description;
    this.owner = owner;
    this.status = status;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.isEmpty()) {
    } else {
      this.name = name;
    }
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    if (language == null || language.isEmpty()) {
    } else {
      this.language = language;
    }
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    if (categoryName == null || categoryName.isEmpty()) {
    } else {
      this.categoryName = categoryName;
    }
  }

  public String getProviderName() {
    return providerName;
  }

  public void setProviderName(String providerName) {
    if (providerName == null || providerName.isEmpty()) {
    } else {
      this.providerName = providerName;
    }
  }

  public int getNumberOfVideos() {
    return numberOfVideos;
  }

  public void setNumberOfVideos(int numberOfVideos) {
    this.numberOfVideos = numberOfVideos;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
    } else {
      this.description = description;
    }
  }

  public AuthorDTO getOwner() {
    return owner;
  }

  public void setOwner(AuthorDTO owner) {
    this.owner = owner;
  }

  public StatusDTO getStatus() {
    return status;
  }

  public void setStatus(StatusDTO status) {
    this.status = status;
  }

//  public String getDifficulty() {
//    return difficulty;
//  }
//
//  public void setDifficulty(String difficulty) {
//    this.difficulty = difficulty;
//  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    PlaylistDTO that = (PlaylistDTO) o;

    if (numberOfVideos != that.numberOfVideos) return false;
    if (id != null ? !id.equals(that.id) : that.id != null) return false;
    if (name != null ? !name.equals(that.name) : that.name != null) return false;
    if (language != null ? !language.equals(that.language) : that.language != null) return false;
    if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
    if (providerName != null ? !providerName.equals(that.providerName) : that.providerName != null) return false;
    if (description != null ? !description.equals(that.description) : that.description != null) return false;
    if (owner != null ? !owner.equals(that.owner) : that.owner != null) return false;
    return !(status != null ? !status.equals(that.status) : that.status != null);

  }

  @Override
  public int hashCode() {
    int result = id != null ? id.hashCode() : 0;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (language != null ? language.hashCode() : 0);
    result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
    result = 31 * result + (providerName != null ? providerName.hashCode() : 0);
    result = 31 * result + numberOfVideos;
    result = 31 * result + (description != null ? description.hashCode() : 0);
    result = 31 * result + (owner != null ? owner.hashCode() : 0);
    result = 31 * result + (status != null ? status.hashCode() : 0);
    return result;
  }
}
