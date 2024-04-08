package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class RoomTypeNotFoundException extends EntityNotFoundExceptionBase {
    public RoomTypeNotFoundException(String message) {
        super(message);
    }
}
