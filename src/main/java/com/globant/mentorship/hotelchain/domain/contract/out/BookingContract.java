package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.mentorship.hotelchain.domain.enumeration.BookingStatusEnum;
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

    @NotNull(message = "Check In date can't be null or empty")
    @FutureOrPresent(message = "Check In date is not valid")
    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    @Builder.Default
    private String status = BookingStatusEnum.ACTIVE.getDescription();

    @Positive(message = "The room ID must be greater than zero")
    @NotNull(message = "Room ID can't be null")
    private Long roomId;

    @Positive(message = "The user ID must be greater than zero")
    @NotNull(message = "Room ID date can't be null")
    private Long userId;
}
