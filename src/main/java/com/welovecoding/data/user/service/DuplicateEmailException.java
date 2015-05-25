package com.welovecoding.data.user.service;

public class DuplicateEmailException extends Exception {

  public DuplicateEmailException(String message) {
    super(message);
  }
}
