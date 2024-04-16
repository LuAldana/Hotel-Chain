package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.domain.entity.BookingEntity;
import com.globant.mentorship.hotelchain.domain.entity.ObservationEntity;
import com.globant.mentorship.hotelchain.domain.enumeration.BookingStatusEnum;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.mapper.ObservationMapper;
import com.globant.mentorship.hotelchain.mapper.RoomMapper;
import com.globant.mentorship.hotelchain.mapper.UserMapper;
import com.globant.mentorship.hotelchain.repository.IBookingRepository;
import com.globant.mentorship.hotelchain.repository.IObservationRepository;
import com.globant.mentorship.hotelchain.service.IBookingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingServiceImpl implements IBookingService {

    private final IBookingRepository bookingRepository;
    private final IObservationRepository observationRepository;
    private final BookingMapper bookingMapper;
    private final RoomMapper roomMapper;
    private final UserMapper userMapper;
    private final ObservationMapper observationMapper;

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
    public BookingContract cancelBooking(Long bookingId, ObservationContract observationContract) {

        BookingEntity bookingEntity = bookingRepository.findById(bookingId).get();
        ObservationEntity observationEntity = observationMapper.loadEntityOut(observationContract);

        bookingEntity.setStatus(BookingStatusEnum.CANCELLED.getDescription());
        observationEntity.setBookingEntity(bookingEntity);

        try {
            bookingRepository.save(bookingEntity);
            observationRepository.save(observationEntity);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }

        return bookingMapper.loadContractOut(bookingEntity);
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

        try {
            bookingRepository.save(bookingEntity);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }

        return bookingContract;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfBookingAlreadyExists(BookingContract bookingContract) {
        return bookingRepository.existsByCheckInDateAndRoomEntityIdAndUserEntityId(bookingContract.getCheckInDate(),
                bookingContract.getRoomId(), bookingContract.getUserId());
    }
}
