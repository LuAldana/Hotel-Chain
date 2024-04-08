package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class CityNotFoundException extends EntityNotFoundExceptionBase {
    public CityNotFoundException(String message) {
        super(message);
    }
}
