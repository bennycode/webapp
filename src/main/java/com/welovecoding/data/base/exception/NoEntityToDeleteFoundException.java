package com.welovecoding.data.base.exception;

public class NoEntityToDeleteFoundException extends ServiceException {

    public NoEntityToDeleteFoundException() {
    }

    public NoEntityToDeleteFoundException(String msg) {
        super(msg);
    }
}
