package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;

import java.util.List;

public interface IBookingService {

    List<BookingContract> getAll();
}
