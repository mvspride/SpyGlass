package com.codedifferently.groupone.SpyGlass.exceptions.user;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException(Long userId) {
        super(userId + " already present in db");
    }
}
