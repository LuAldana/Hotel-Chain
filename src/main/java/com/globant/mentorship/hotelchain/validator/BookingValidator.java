package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.controller.payload.ObservationPayload;
import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.domain.enumeration.BookingStatusEnum;
import com.globant.mentorship.hotelchain.domain.enumeration.RoomTypeEnum;
import com.globant.mentorship.hotelchain.exception.*;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.service.IRoomService;
import com.globant.mentorship.hotelchain.service.IRoomTypeService;
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
    private final IRoomTypeService roomTypeService;

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

    public void cancelBookingValidator(ObservationPayload observationPayload) {

        BookingContract bookingContract = bookingService.getBookingById(observationPayload.getBookingId());

        if(Objects.isNull(bookingContract))
            throw new BookingNotFoundException(String.format("Booking ID %d not found", observationPayload.getBookingId()));

        if(Objects.equals(bookingContract.getStatus(), BookingStatusEnum.CANCELLED.getDescription()))
            throw new BookingValidatorException("Booking is already canceled");

        if(Objects.equals(bookingContract.getStatus(), BookingStatusEnum.CHECK_OUT.getDescription()))
            throw new BookingValidatorException("Booking must be active");

        RoomContract roomContract = roomService.getRoomById(bookingContract.getRoomId());
        RoomTypeContract roomTypeContract = roomTypeService.getRoomType(roomContract.getRoomTypeId());

        if(roomTypeContract.getName().equals(RoomTypeEnum.SUITE.getType()))
            throw new BookingValidatorException("This action is not available for Suite type rooms");
    }

    public BookingContract getBookingTraceabilityValidator(Long bookingId) {

        BookingContract bookingContract = bookingService.getBookingById(bookingId);

        if(Objects.isNull(bookingContract))
            throw new BookingNotFoundException(String.format("Booking ID %d not found", bookingId));

        if(!bookingContract.getStatus().equals(BookingStatusEnum.ACTIVE.getDescription()))
            throw new BookingValidatorException("Booking must be active");

        return bookingContract;
    }
}
