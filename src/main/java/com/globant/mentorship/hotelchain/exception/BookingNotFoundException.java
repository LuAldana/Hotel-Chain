package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class BookingNotFoundException extends EntityNotFoundExceptionBase {

    public BookingNotFoundException(String message) {
        super(message);
    }
}
