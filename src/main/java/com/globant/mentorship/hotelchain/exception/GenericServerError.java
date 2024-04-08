package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.HotelChainExceptionBase;

public class GenericServerError extends HotelChainExceptionBase {

    public GenericServerError(String message, Throwable cause) {
        super(message, cause);
    }
}
