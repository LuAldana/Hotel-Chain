package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/booking")
public interface IBookingController {
    @GetMapping
    ResponseEntity<List<BookingContract>> getAll();
}
