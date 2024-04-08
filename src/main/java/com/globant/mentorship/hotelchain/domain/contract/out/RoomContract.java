package com.globant.mentorship.hotelchain.domain.contract.out;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomContract {

    @NotNull(message = "Number room can't be null or empty")
    @Positive(message = "Number room must be greater than zero")
    private int number;
    @NotNull(message = "You must indicate whether or not the room has a private bathroom.")
    private boolean hasPrivateBath;
    @NotNull(message = "You must indicate whether or not the room has a phone")
    private boolean hasPhone;
    @NotNull(message = "You must indicate whether or not the room has a heating.")
    private boolean hasHeating;
    @NotEmpty(message = "Status room can't be null or empty")
    private String status;
    @NotNull(message = "Hotel site id mustn't be null or empty")
    @Positive(message = "Hotel site id must be greater than zero")
    private Long hotelSiteId;
    @NotNull(message = "Room type id mustn't be null or empty")
    @Positive(message = "Room type id  must be greater than zero")
    private Long roomTypeId;

}
