package com.welovecoding.data.base.exception;

public class NoEntityToUpdateFoundException extends ServiceException {

  public NoEntityToUpdateFoundException() {
  }

  public NoEntityToUpdateFoundException(String msg) {
    super(msg);
  }
}
