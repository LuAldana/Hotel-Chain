package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObservationContract {

    @JsonIgnore
    private Long id;

    @PositiveOrZero(message = "Type must be greater than zero")
    @NotNull(message = "Type of observation can't be null or empty")
    private Integer type;

    @NotEmpty(message = "Detail of observation can't be null or empty")
    private String observation;

    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    @Positive(message = "Booking ID must be greater than zero")
    @NotNull(message = "Booking ID can't be null or empty")
    private Long bookingId;
}
