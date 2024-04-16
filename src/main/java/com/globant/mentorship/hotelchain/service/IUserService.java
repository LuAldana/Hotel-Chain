package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.domain.dto.UserBookingDto;
import org.springframework.lang.Nullable;

public interface IUserService {

    UserContract getUserByDocument(Long document);

    UserContract getUserById(Long id);

    void blockGuest(Long userId, @Nullable ObservationContract observationContract);

    UserBookingDto getUserIdWithMostBookings();
}
