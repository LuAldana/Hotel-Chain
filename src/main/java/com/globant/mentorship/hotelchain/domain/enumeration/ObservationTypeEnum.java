package com.globant.mentorship.hotelchain.domain.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ObservationTypeEnum {

    CANCELLATION(0),

    POSTPONEMENT(1),

    CHANGE_ROOM(2);

    private final int numericType;

}
