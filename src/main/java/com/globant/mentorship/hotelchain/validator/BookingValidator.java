package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.exception.BookingAlreadyExistsException;
import com.globant.mentorship.hotelchain.exception.RoomNotFoundException;
import com.globant.mentorship.hotelchain.exception.UserNotFoundException;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.service.IRoomService;
import com.globant.mentorship.hotelchain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class BookingValidator {

    public final IBookingService bookingService;
    public final IRoomService roomService;
    private final IUserService userService;

    public Map<String, Object> createBookingValidator(BookingContract bookingContract) {

        Map<String, Object> mapContractValidated = new HashMap<>();

        RoomContract roomContract = roomService.getRoomById(bookingContract.getRoomId());

        if(Objects.isNull(roomContract))
            throw new RoomNotFoundException(String.format("Room ID %d not found", bookingContract.getRoomId()));

        UserContract userContract = userService.getUserById(bookingContract.getUserId());

        if(Objects.isNull(userContract))
            throw new UserNotFoundException(String.format("User ID %d not found", bookingContract.getUserId()));

        if(bookingService.validateIfBookingAlreadyExists(bookingContract))
            throw new BookingAlreadyExistsException(("Booking already exists for this guest"));

        mapContractValidated.put("room", roomContract);
        mapContractValidated.put("user", userContract);
        mapContractValidated.put("booking", bookingContract);

        return mapContractValidated;
    }
}
