package com.globant.mentorship.hotelchain.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BookingStatusEnum {

    CANCELLED("CANCELADA"),

    CHECK_OUT("CHECK OUT"),

    ACTIVE("ACTIVA");

    private final String description;

}
