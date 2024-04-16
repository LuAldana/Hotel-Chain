package com.globant.mentorship.hotelchain.controller.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ObservationPayload {

    @Positive(message = "Booking ID must be greater than zero")
    @NotNull(message = "Booking ID can't be null or empty")
    private Long bookingId;
    @NotEmpty(message = "Detail of observation can't be null or empty")
    private String observation;
}
