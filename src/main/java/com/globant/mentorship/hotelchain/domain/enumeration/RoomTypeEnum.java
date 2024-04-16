package com.globant.mentorship.hotelchain.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoomTypeEnum {

    SIMPLE("Simple"),

    DOUBLE("Doble"),

    SUITE("Suite");

    private final String type;

}
