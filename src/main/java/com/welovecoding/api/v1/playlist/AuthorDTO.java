package com.welovecoding.api.v1.playlist;

import org.codehaus.jackson.annotate.JsonPropertyOrder;

@JsonPropertyOrder({
  "name",
  "website",
  "description"
})
public class AuthorDTO {

  private String name;
  private String website;
  private String description;

  public AuthorDTO() {
  }

  public AuthorDTO(String name, String website, String description) {
    this.name = name;
    this.website = website;
    this.description = description;
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

  public String getWebsite() {
    return website;
  }

  public void setWebsite(String website) {
    if (website == null || website.isEmpty()) {
    } else {
      this.website = website;
    }
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    AuthorDTO authorDTO = (AuthorDTO) o;

    if (name != null ? !name.equals(authorDTO.name) : authorDTO.name != null) return false;
    if (website != null ? !website.equals(authorDTO.website) : authorDTO.website != null) return false;
    return !(description != null ? !description.equals(authorDTO.description) : authorDTO.description != null);

  }

  @Override
  public int hashCode() {
    int result = name != null ? name.hashCode() : 0;
    result = 31 * result + (website != null ? website.hashCode() : 0);
    result = 31 * result + (description != null ? description.hashCode() : 0);
    return result;
  }
}
