package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;

public class RoomNotFoundException extends EntityNotFoundExceptionBase {
    public RoomNotFoundException(String message) {
        super(message);
    }
}
