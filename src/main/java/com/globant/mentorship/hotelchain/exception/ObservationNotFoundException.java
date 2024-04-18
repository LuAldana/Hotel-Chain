package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class ObservationNotFoundException extends EntityNotFoundExceptionBase {

    public ObservationNotFoundException(String message) {
        super(message);
    }
}
