package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.mapper.UserMapper;
import com.globant.mentorship.hotelchain.repository.IUserRepository;
import com.globant.mentorship.hotelchain.service.IBookingService;
import com.globant.mentorship.hotelchain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final IBookingService bookingService;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserContract getUserByDocument(Long document) {
        return userMapper.loadContractOut(userRepository.findByDocument(document).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public UserContract getUserById(Long id) {
        return userMapper.loadContractOut(userRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public void blockGuest(Long userId, ObservationContract observationContract) {

        if(Objects.nonNull(observationContract)) {
            List<BookingContract> bookingContracts = bookingService.getBookingListByUserId(userId);
            bookingContracts.forEach(b -> bookingService.cancelBooking(ObservationContract.builder()
                    .bookingId(b.getId())
                    .observation(observationContract.getObservation())
                    .build()));
        }
        userRepository.blockGuest(userId);
    }
}
