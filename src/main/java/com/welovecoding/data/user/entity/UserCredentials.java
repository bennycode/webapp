package com.welovecoding.data.user.entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(
  uniqueConstraints
    = @UniqueConstraint(columnNames = {UserCredentials.CREDENTIAL_TYPE_COLUMN_NAME, "USER_ID"})
)
@Entity
@DiscriminatorColumn(name = UserCredentials.CREDENTIAL_TYPE_COLUMN_NAME)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class UserCredentials {

  private static final long serialVersionUID = 1L;
  public static final String CREDENTIAL_TYPE_COLUMN_NAME = "CRED_TYPE";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  private String token;

  @ManyToOne(cascade = CascadeType.ALL)
  @NotNull
  private User user;

//  @Column(name = CREDENTIAL_TYPE_COLUMN_NAME, insertable = false, updatable = false)
private String credType;


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getCredType() {
    return credType;
  }

  public void setCredType(String credType) {
    this.credType = credType;
  }

}
