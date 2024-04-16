package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IUserController;
import com.globant.mentorship.hotelchain.controller.payload.BlockUserPayload;
import com.globant.mentorship.hotelchain.controller.payload.ObservationPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.dto.UserBookingDto;
import com.globant.mentorship.hotelchain.initializer.ObservationInitializer;
import com.globant.mentorship.hotelchain.service.IUserService;
import com.globant.mentorship.hotelchain.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements IUserController {

    private final IUserService userService;
    private final UserValidator userValidator;
    private final ObservationInitializer observationInitializer;

    @Override
    public ResponseEntity<Void> blockGuest(BlockUserPayload blockUserPayload) {
        boolean hasBooking = userValidator.blockGuestValidator(blockUserPayload);

        if(hasBooking) {
            ObservationPayload observationPayload = ObservationPayload.builder()
                    .observation(blockUserPayload.getObservation())
                    .build();
            ObservationContract observationContract = observationInitializer.init(observationPayload);

            userService.blockGuest(blockUserPayload.getUserId(), observationContract);
        } else {
            userService.blockGuest(blockUserPayload.getUserId(), null);
        }

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserBookingDto> getGuestWithMostBookings() {
        return ResponseEntity.ok(userService.getUserIdWithMostBookings());
    }
}
