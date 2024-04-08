package com.globant.mentorship.hotelchain.domain.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErrorDto {
    private final HttpStatus status;
    private final String message;
}
