package com.globant.mentorship.hotelchain.initializer;

import com.globant.mentorship.hotelchain.controller.payload.ObservationPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.enumeration.ObservationTypeEnum;
import org.springframework.stereotype.Component;

@Component
public class ObservationInitializer {

    public ObservationContract init(ObservationPayload payload) {

        return ObservationContract.builder()
                .type(ObservationTypeEnum.CANCELLATION.getNumericType())
                .observation(payload.getObservation())
                .bookingId(payload.getBookingId())
                .build();
    }
}
