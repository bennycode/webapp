package com.welovecoding.data.user.entity;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(GoogleUserCredentials.CREDENTIAL_TYPE_COLUMN_VALUE)
public class GoogleUserCredentials extends UserCredentials {

  public static final String CREDENTIAL_TYPE_COLUMN_VALUE = "GOOGLE";

  public GoogleUserCredentials() {
  }

  public GoogleUserCredentials(String token) {
    super.setToken(token);
  }
}
