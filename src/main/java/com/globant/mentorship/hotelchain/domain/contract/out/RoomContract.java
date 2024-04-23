package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.globant.mentorship.hotelchain.domain.enumeration.RoomStatusEnum;
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

    @JsonIgnore
    private Long id;

    @NotNull(message = "Number room can't be null or empty")
    @Positive(message = "Number room must be greater than zero")
    private Integer number;

    @NotNull(message = "You must indicate whether or not the room has a private bathroom.")
    private boolean hasPrivateBath;

    @NotNull(message = "You must indicate whether or not the room has a phone")
    private boolean hasPhone;

    @NotNull(message = "You must indicate whether or not the room has a heating.")
    private boolean hasHeating;

    @Builder.Default
    private String status = RoomStatusEnum.AVAILABLE.getDescription();

    @NotNull(message = "Hotel site id mustn't be null or empty")
    @Positive(message = "Hotel site id must be greater than zero")
    private Long hotelSiteId;

    @NotNull(message = "Room type id mustn't be null or empty")
    @Positive(message = "Room type id  must be greater than zero")
    private Long roomTypeId;

}
