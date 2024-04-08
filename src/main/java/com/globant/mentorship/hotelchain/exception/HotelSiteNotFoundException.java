package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class HotelSiteNotFoundException extends EntityNotFoundExceptionBase {

    public HotelSiteNotFoundException(String message) {
        super(message);
    }
}