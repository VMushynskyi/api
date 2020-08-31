package com.api.testapi.exceptions;

public class LocationNotFoundException extends RuntimeException {
    public LocationNotFoundException(Long id){
        super("Location not found" + id);
    }
}
