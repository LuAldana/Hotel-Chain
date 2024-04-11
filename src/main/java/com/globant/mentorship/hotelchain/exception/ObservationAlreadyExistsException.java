package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityAlreadyExistsExceptionBase;

public class ObservationAlreadyExistsException extends EntityAlreadyExistsExceptionBase {

    public ObservationAlreadyExistsException(String message) {
        super(message);
    }
}
