package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.entity.ObservationEntity;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.mapper.ObservationMapper;
import com.globant.mentorship.hotelchain.repository.IObservationRepository;
import com.globant.mentorship.hotelchain.service.IObservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class ObservationServiceImpl implements IObservationService {

    private final IObservationRepository observationRepository;
    private final ObservationMapper observationMapper;
    private final BookingMapper bookingMapper;

    @Override
    @Transactional
    public ObservationContract createObservation(Map<String, Object> mapContractValidated) {

        ObservationContract observationContract = (ObservationContract) mapContractValidated.get("observation");
        BookingContract bookingContract = (BookingContract) mapContractValidated.get("booking");

        ObservationEntity observationEntity = observationMapper.loadEntityOut(observationContract);
        observationEntity.setBookingEntity(bookingMapper.loadEntityOut(bookingContract));

        try {
            observationRepository.save(observationEntity);
        } catch (Exception e) {
             throw new GenericServerError(e.getLocalizedMessage(), e.getCause());
        }

        return observationContract;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfObservationAlreadyExists(ObservationContract observationContract) {
        return observationRepository.existsByTypeAndBookingEntityId(observationContract.getType(), observationContract.getBookingId());
    }
}
