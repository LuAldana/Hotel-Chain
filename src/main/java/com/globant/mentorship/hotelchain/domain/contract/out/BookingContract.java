package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingContract {

    @JsonIgnore
    private Long id;

    @NotNull(message = "Check In date can't be null")
    @FutureOrPresent(message = "Check In date is not valid")
    private LocalDate checkInDate;

    @NotNull(message = "Check Out date can't be null")
    @Future(message = "Check Out date is not valid")
    private LocalDate checkOutDate;

    @Builder.Default
    private String status = "ACTIVA";

    @Positive(message = "The room ID must be greater than zero")
    private Long roomId;

    @Positive(message = "The user ID must be greater than zero")
    private Long userId;
}
