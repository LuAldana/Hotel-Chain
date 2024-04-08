package com.globant.mentorship.hotelchain.exception.base;

public class HotelChainExceptionBase extends RuntimeException {
    public HotelChainExceptionBase(String message) {
        super(message);
    }

    public HotelChainExceptionBase(String message, Throwable cause) {
        super(message, cause);
    }

}
