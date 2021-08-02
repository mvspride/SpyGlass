package com.codedifferently.groupone.SpyGlass.exceptions.user;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(Long userId) {
        super(userId + " not found");
    }
}
