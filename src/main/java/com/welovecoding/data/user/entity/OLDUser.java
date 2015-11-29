//package com.welovecoding.data.user.entity;
//
//import com.welovecoding.data.base.SlugBaseEntity;
//
//import javax.persistence.*;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import java.util.Date;
//
//@Entity
//public class User extends SlugBaseEntity<Long> {
//
//  @NotNull
//  @Size(min = 1, max = 255)
//  @Basic(optional = false)
//  private String username;
//
//  @Column(name = "email", length = 100, nullable = false, unique = true)
//  private String email;
//
//  @Column(name = "first_name", length = 100, nullable = false)
//  private String firstName;
//
//  @Column(name = "last_name", length = 100, nullable = false)
//  private String lastName;
//
//  @Column(name = "password", length = 255)
//  private String password;
//
//  @Enumerated(EnumType.STRING)
//  @Column(name = "role", length = 20, nullable = false)
//  private Role role;
//
//
//  public User() {
//  }
//
//  User(Long id, Date created, Date lastModified, String slug, String username, String email, String firstName, String lastName, String password, Role role) {
//    super(id, created, lastModified, slug);
//    this.username = username;
//    this.email = email;
//    this.firstName = firstName;
//    this.lastName = lastName;
//    this.password = password;
//    this.role = role;
//  }
//
//  @Override
//  public Comparable comparableAttribute() {
//    return getUsername();
//  }
//
//  @Override
//  protected String getSlugableName() {
//    return getUsername();
//  }
//
//  public String getUsername() {
//    return username;
//  }
//
//  public void setUsername(String username) {
//    this.username = username;
//  }
//
//  public String getEmail() {
//    return email;
//  }
//
//  public void setEmail(String email) {
//    this.email = email;
//  }
//
//  public String getFirstName() {
//    return firstName;
//  }
//
//  public void setFirstName(String firstName) {
//    this.firstName = firstName;
//  }
//
//  public String getLastName() {
//    return lastName;
//  }
//
//  public void setLastName(String lastName) {
//    this.lastName = lastName;
//  }
//
//  public String getPassword() {
//    return password;
//  }
//
//  public void setPassword(String password) {
//    this.password = password;
//  }
//
//  public Role getRole() {
//    return role;
//  }
//
//  public void setRole(Role role) {
//    this.role = role;
//  }
//
//}
