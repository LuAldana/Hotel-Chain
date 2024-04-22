package com.globant.mentorship.hotelchain.domain.dto;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookingTraceabilityResponse {

    private BookingContract bookingContract;
    private List<ObservationContract> observations;

}
