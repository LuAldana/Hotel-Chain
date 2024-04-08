package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.repository.IBookingRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @InjectMocks
    BookingServiceImpl bookingService;
    @Mock
    IBookingRepository bookingRepository;
    @Mock
    BookingMapper bookingMapper;

    @Test
    void getAll() {

        // Given
        List<BookingEntity> bookingEntities = new ArrayList<>() {{
            add(BookingEntity.builder().id(1L).build());
            add(BookingEntity.builder().id(2L).build());
        }};
        List<BookingContract> bookingContracts = new ArrayList<>() {{
            add(BookingContract.builder().status("CANCELED").build());
            add(BookingContract.builder().status("CHECK OUT").build());
        }};

        when(bookingMapper.loadContractsOut(bookingEntities)).thenReturn(bookingContracts);
        when(bookingRepository.findAll()).thenReturn(bookingEntities);

        // When
        List<BookingContract> bookingListFound = bookingService.getAll();

        // Then
        assertThat(bookingListFound).isNotNull();
        verify(bookingMapper, times(1)).loadContractsOut(bookingEntities);
        verify(bookingRepository, times(1)).findAll();
    }
}