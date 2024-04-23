package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.controller.payload.BlockUserPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.exception.UserNotFoundException;
import com.globant.mentorship.hotelchain.exception.UserValidatorException;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final IUserService userService;
    private final IBookingService bookingService;

    public boolean blockGuestValidator(BlockUserPayload blockUserPayload) {

        UserContract userContract = userService.getUserById(blockUserPayload.getUserId());

        if(Objects.isNull(userContract))
            throw new UserNotFoundException(String.format("User ID %s not found", blockUserPayload.getUserId()));

        if(!userContract.isStatus())
            throw new UserValidatorException("Guest is already blocked");

        List<BookingContract> bookingContracts = bookingService.getBookingListByUserId(blockUserPayload.getUserId());

        return Objects.nonNull(bookingContracts);
    }

}
