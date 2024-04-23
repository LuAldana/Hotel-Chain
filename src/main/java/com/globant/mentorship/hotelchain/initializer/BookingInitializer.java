package com.globant.mentorship.hotelchain.initializer;

import com.globant.mentorship.hotelchain.controller.payload.BookingPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import org.springframework.stereotype.Component;

@Component
public class BookingInitializer {

    public BookingContract init(BookingPayload payload) {

        return BookingContract.builder()
                .checkInDate(payload.getCheckInDate())
                .checkOutDate(payload.getCheckInDate().plusDays(5))
                .roomId(payload.getRoomId())
                .userId(payload.getUserId())
                .build();
    }
}
