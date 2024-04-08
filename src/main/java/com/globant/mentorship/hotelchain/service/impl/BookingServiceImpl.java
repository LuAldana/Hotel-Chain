package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.repository.IBookingRepository;
import com.globant.mentorship.hotelchain.service.IBookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final BookingMapper bookingMapper;

    @Override
    public List<BookingContract> getAll() {
        return bookingMapper.loadContractsOut(bookingRepository.findAll());
    }
}
