package com.globant.mentorship.hotelchain.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserBookingDto {

    private String document;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private Integer numBookings;
}
