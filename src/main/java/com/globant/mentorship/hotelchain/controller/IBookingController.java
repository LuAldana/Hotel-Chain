package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import jakarta.validation.Valid;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Validated
@RequestMapping("/booking")
public interface IBookingController {

    @GetMapping
    ResponseEntity<List<BookingContract>> getAll();

    @PostMapping
    ResponseEntity<BookingContract>  createBooking(@RequestBody @Valid BookingContract bookingContract);
}
