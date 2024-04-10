package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityAlreadyExistsExceptionBase;

public class BookingAlreadyExistsException extends EntityAlreadyExistsExceptionBase {

    public BookingAlreadyExistsException(String message) {
        super(message);
    }
}
