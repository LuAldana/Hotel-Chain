package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class UserNotFoundException extends EntityNotFoundExceptionBase {

    public UserNotFoundException(String message) {
        super(message);
    }
}
