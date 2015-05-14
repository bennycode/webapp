package com.welovecoding.data.user.entity;


import com.welovecoding.data.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
//@NamedQueries({
//  @NamedQuery(name = FIND_BY_EMAIL, query = "SELECT u FROM User u WHERE u.email = :email")
//})
public class User extends BaseEntity<Long> {

  //  public static final String FIND_BY_EMAIL = "User.findByEmail";
//
  @NotNull
  @Column(unique = true)
  private String email;

  //
//  @OneToMany(cascade = CascadeType.ALL, targetEntity = UserCredentials.class, mappedBy = "user")
//  private List<UserCredentials> credentials;
//
//  private boolean admin;
//
  public User() {
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
//
//  public List<UserCredentials> getCredentials() {
//    return credentials;
//  }
//
//  public void setCredentials(List<UserCredentials> credentials) {
//    this.credentials = credentials;
//  }
//
//  public boolean isAdmin() {
//    return admin;
//  }
//
//  public void setAdmin(boolean admin) {
//    this.admin = admin;
//  }

}
