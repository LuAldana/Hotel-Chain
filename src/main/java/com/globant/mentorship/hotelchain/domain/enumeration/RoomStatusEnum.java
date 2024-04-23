package com.globant.mentorship.hotelchain.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomStatusEnum {

    AVAILABLE("DISPONIBLE"),

    BOOKED("RESERVADA");

    private final String description;
}
