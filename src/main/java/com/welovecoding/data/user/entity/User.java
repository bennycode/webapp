package com.welovecoding.data.user.entity;


import com.welovecoding.data.base.BaseEntity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

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


  @OneToMany(cascade = CascadeType.ALL, targetEntity = UserCredentials.class, mappedBy = "user")
  private List<UserCredentials> credentials;

  private boolean admin;

  public User() {
  }

  User(Long id, Date created, Date lastModified, String email, List<UserCredentials> credentials, boolean admin) {
    super(id, created, lastModified);
    this.email = email;
    this.credentials = credentials;
    this.admin = admin;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public List<UserCredentials> getCredentials() {
    return credentials;
  }

  public void setCredentials(List<UserCredentials> credentials) {
    this.credentials = credentials;
  }

  public boolean isAdmin() {
    return admin;
  }

  public void setAdmin(boolean admin) {
    this.admin = admin;
  }

}
