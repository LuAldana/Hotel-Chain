package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.exception.BookingNotFoundException;
import com.globant.mentorship.hotelchain.exception.ObservationAlreadyExistsException;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.service.IObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ObservationValidator {

    private final IObservationService observationService;
    private final IBookingService bookingService;

    public Map<String, Object> createObservationValidator(ObservationContract observationContract) {

        Map<String, Object> mapContractValidated = new HashMap<>();

        BookingContract bookingContract = bookingService.getBookingById(observationContract.getBookingId());

        if(Objects.isNull(bookingContract))
            throw new BookingNotFoundException(String.format("Booking ID %d not found", observationContract.getBookingId()));

        if(observationService.validateIfObservationAlreadyExists(observationContract))
            throw new ObservationAlreadyExistsException(String.format("The type %d observation already exists for this booking", observationContract.getType()));

        mapContractValidated.put("booking", bookingContract);
        mapContractValidated.put("observation", observationContract);

        return mapContractValidated;
    }
}
