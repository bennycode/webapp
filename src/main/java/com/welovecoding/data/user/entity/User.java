package com.welovecoding.data.user.entity;

import com.welovecoding.data.base.SlugBaseEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class User extends SlugBaseEntity<Long> {

  @NotNull
  @Size(min = 1, max = 255)
  @Basic(optional = false)
  private String username;

  @Column(name = "email", length = 100, nullable = false, unique = true)
  private String email;

  @Column(name = "first_name", length = 100, nullable = false)
  private String firstName;

  @Column(name = "last_name", length = 100, nullable = false)
  private String lastName;

  @Column(name = "password", length = 255)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", length = 20, nullable = false)
  private Role role;

  @Enumerated(EnumType.STRING)
  @Column(name = "sign_in_provider", length = 20)
  private SocialMediaService signInProvider;

  public User() {
  }

  User(Long id, Date created, Date lastModified, String slug, String username, String email, String firstName, String lastName, String password, Role role, SocialMediaService signInProvider) {
    super(id, created, lastModified, slug);
    this.username = username;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.password = password;
    this.role = role;
    this.signInProvider = signInProvider;
  }

  @Override
  public Comparable comparableAttribute() {
    return getUsername();
  }

  @Override
  protected String getSlugableName() {
    return getUsername();
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public static Builder getBuilder() {
    return new Builder();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public SocialMediaService getSignInProvider() {
    return signInProvider;
  }

  public void setSignInProvider(SocialMediaService signInProvider) {
    this.signInProvider = signInProvider;
  }


  public static class Builder {

    private User user;

    public Builder() {
      user = new User();
      user.role = Role.ROLE_USER;
    }

    public Builder id(Long id) {
      user.setId(id);
      return this;
    }

    public Builder role(Role role) {
      user.role = role;
      return this;
    }

    public Builder email(String email) {
      user.email = email;
      return this;
    }

    public Builder firstName(String firstName) {
      user.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      user.lastName = lastName;
      return this;
    }

    public Builder password(String password) {
      user.password = password;
      return this;
    }

    public Builder signInProvider(SocialMediaService signInProvider) {
      user.signInProvider = signInProvider;
      return this;
    }

    public User build() {
      return user;
    }
  }
}
