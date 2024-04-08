package com.globant.mentorship.hotelchain.exception;

import com.globant.mentorship.hotelchain.exception.base.EntityAlreadyExistsExceptionBase;

public class RoomAlreadyExistsException extends EntityAlreadyExistsExceptionBase {
    public RoomAlreadyExistsException(String message) {
        super(message);
    }
}
