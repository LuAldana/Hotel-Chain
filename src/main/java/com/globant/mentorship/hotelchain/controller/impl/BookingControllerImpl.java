package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IBookingController;
import com.globant.mentorship.hotelchain.controller.payload.ObservationPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.dto.BookingTraceabilityResponse;
import com.globant.mentorship.hotelchain.initializer.ObservationInitializer;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.validator.BookingValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class BookingControllerImpl implements IBookingController {

    private final IBookingService bookingService;
    private final BookingValidator bookingValidator;
    private final ObservationInitializer observationInitializer;

    @Override
    public ResponseEntity<List<BookingContract>> getAll() {
        return ResponseEntity.ok(bookingService.getAll());
    }

    @Override
    public ResponseEntity<List<BookingContract>> getAllActiveBookings() {
        return ResponseEntity.ok(bookingService.getAllActiveBookings());
    }

    @Override
    public ResponseEntity<BookingTraceabilityResponse> getBookingTraceability(Long bookingId) {
        BookingContract bookingContractValidated = bookingValidator.getBookingTraceabilityValidator(bookingId);
        return ResponseEntity.ok(bookingService.getBookingTraceability(bookingContractValidated));
    }

    @Override
    public ResponseEntity<BookingContract> createBooking(BookingContract bookingContract) {
        Map<String, Object> mapContractValidated = bookingValidator.createBookingValidator(bookingContract);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.createBooking(mapContractValidated));
    }

    @Override
    public ResponseEntity<BookingContract> cancelBooking(ObservationPayload payload) {
        bookingValidator.cancelBookingValidator(payload);
        ObservationContract observationContract = observationInitializer.init(payload);
        bookingService.cancelBooking(observationContract);
        return ResponseEntity.ok().build();
    }

}
