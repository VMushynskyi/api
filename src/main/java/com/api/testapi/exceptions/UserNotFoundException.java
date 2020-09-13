package com.api.testapi.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id){
        super("User not found" + id);
    }
}
