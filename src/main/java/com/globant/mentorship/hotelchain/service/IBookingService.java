package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;

import java.util.List;
import java.util.Map;

public interface IBookingService {

    List<BookingContract> getAll();

    BookingContract getBookingById(Long id);

    BookingContract cancelBooking(Long bookingId, ObservationContract observationContract);

    BookingContract createBooking(Map<String, Object> mapContractValidated);

    boolean validateIfBookingAlreadyExists(BookingContract bookingContract);
}
