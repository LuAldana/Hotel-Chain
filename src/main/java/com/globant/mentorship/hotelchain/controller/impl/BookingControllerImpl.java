package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IBookingController;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class BookingControllerImpl implements IBookingController {

    private final IBookingService bookingService;

    @Override
    public ResponseEntity<List<BookingContract>> getAll() {
        return ResponseEntity.ok(bookingService.getAll());
    }
}
