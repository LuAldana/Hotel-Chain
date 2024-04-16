package com.globant.mentorship.hotelchain.controller.payload;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BlockUserPayload {

    @Positive(message = "Booking ID must be greater than zero")
    @NotNull(message = "Booking ID can't be null or empty")
    Long userId;
    String observation;
}
