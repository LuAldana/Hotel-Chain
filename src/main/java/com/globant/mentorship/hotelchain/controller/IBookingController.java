package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.controller.payload.ObservationPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.dto.BookingTraceability;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/booking")
public interface IBookingController {

    @GetMapping
    ResponseEntity<List<BookingContract>> getAll();

    @GetMapping("/actives")
    ResponseEntity<List<BookingContract>> getAllActiveBookings();

    @GetMapping("/traceability")
    ResponseEntity<BookingTraceability> getBookingTraceability(@RequestParam("bookingId") Long bookingId);

    @PostMapping
    ResponseEntity<BookingContract>  createBooking(@RequestBody @Valid BookingContract bookingContract);

    @PatchMapping("/cancel")
    ResponseEntity<BookingContract> cancelBooking(@RequestBody @Valid ObservationPayload payload);
}
