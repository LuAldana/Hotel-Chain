package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.dto.ErrorDto;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.exception.base.EntityAlreadyExistsExceptionBase;
import com.globant.mentorship.hotelchain.exception.base.EntityNotFoundExceptionBase;
import com.globant.mentorship.hotelchain.exception.base.ValidatorExceptionBase;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Component
@RestControllerAdvice
@Order(2)
public class HotelChainExceptionHandler  {

    @ExceptionHandler(EntityAlreadyExistsExceptionBase.class)
    public ResponseEntity<ErrorDto> EntityAlreadyExistsExceptionBaseHandler(EntityAlreadyExistsExceptionBase e) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(createErrorDto(HttpStatus.CONFLICT, e));
    }

    @ExceptionHandler(EntityNotFoundExceptionBase.class)
    public ResponseEntity<ErrorDto> EntityNotFoundExceptionBaseHandler(EntityNotFoundExceptionBase e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorDto(HttpStatus.NOT_FOUND, e));
    }

    @ExceptionHandler(ValidatorExceptionBase.class)
    public ResponseEntity<ErrorDto> ValidatorExceptionBaseHandler(ValidatorExceptionBase e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createErrorDto(HttpStatus.BAD_REQUEST, e));
    }

    @ExceptionHandler(GenericServerError.class)
    public ResponseEntity<ErrorDto> GenericServerErrorHandler (GenericServerError e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorDto(HttpStatus.INTERNAL_SERVER_ERROR, e));
    }

    private ErrorDto createErrorDto(HttpStatus statusCode, Exception e) {
        return ErrorDto.builder()
                .status(statusCode)
                .message(e.getMessage())
                .build();
    }

}
