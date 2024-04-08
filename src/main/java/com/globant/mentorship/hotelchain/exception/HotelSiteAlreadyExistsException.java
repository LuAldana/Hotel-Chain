package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityAlreadyExistsExceptionBase;

public class HotelSiteAlreadyExistsException extends EntityAlreadyExistsExceptionBase {

    public HotelSiteAlreadyExistsException(String message) {
        super(message);
    }
}
