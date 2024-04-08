package com.globant.mentorship.hotelchain.domain.contract.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObservationContract {

    private int type;
    private String observation;
    private String date;
    private BookingContract bookingContract;
}
