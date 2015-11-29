package com.welovecoding.api.v1.tutorial;

import com.welovecoding.api.v1.base.BaseDTO;
import com.welovecoding.api.v1.user.dto.UserDTO;

import javax.persistence.Entity;


@Entity
public class AuthorDTO extends BaseDTO<Long> {

  private String firstname;
  private String lastname;
  private String description;
  private String website;
  private String channelUrl;
  private UserDTO user;

  public AuthorDTO() {
  }

  public UserDTO getUser() {
    return user;
  }

  public void setUser(UserDTO user) {
    this.user = user;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
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
