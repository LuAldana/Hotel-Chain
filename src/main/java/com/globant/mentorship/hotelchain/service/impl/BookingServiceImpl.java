package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.mapper.RoomMapper;
import com.globant.mentorship.hotelchain.mapper.UserMapper;
import com.globant.mentorship.hotelchain.repository.IBookingRepository;
import com.globant.mentorship.hotelchain.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final BookingMapper bookingMapper;
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public List<BookingContract> getAll() {
        return bookingMapper.loadContractsOut(bookingRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public BookingContract getBookingById(Long id) {
        return bookingMapper.loadContractOut(bookingRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional
    public BookingContract createBooking(Map<String, Object> mapContractValidated) {

        BookingContract bookingContract = (BookingContract) mapContractValidated.get("booking");
        RoomContract roomContract = (RoomContract) mapContractValidated.get("room");
        UserContract userContract = (UserContract) mapContractValidated.get("user");

        BookingEntity bookingEntity = bookingMapper.loadEntityOut(bookingContract);
        bookingEntity.setRoomEntity(roomMapper.loadEntityOut(roomContract));
        bookingEntity.setUserEntity(userMapper.loadEntityOut(userContract));
        bookingRepository.save(bookingEntity);

        return bookingContract;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfBookingAlreadyExists(BookingContract bookingContract) {
        return bookingRepository.existsByCheckInDateAndRoomIdAndUserId(bookingContract.getCheckInDate(),
                bookingContract.getRoomId(), bookingContract.getUserId()).isPresent();

    }
}
