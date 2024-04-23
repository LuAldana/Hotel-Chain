package com.globant.mentorship.hotelchain.controller.payload;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookingPayload {

    @NotNull(message = "Check In date can't be null or empty")
    @FutureOrPresent(message = "Check In date is not valid")
    private LocalDate checkInDate;

    @Positive(message = "The room ID must be greater than zero")
    @NotNull(message = "Room ID can't be null")
    private Long roomId;

    @Positive(message = "The user ID must be greater than zero")
    @NotNull(message = "Room ID date can't be null")
    private Long userId;}
