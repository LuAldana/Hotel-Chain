package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.BookingContract;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.domain.entity.ObservationEntity;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.exception.ObservationNotFoundException;
import com.globant.mentorship.hotelchain.mapper.BookingMapper;
import com.globant.mentorship.hotelchain.mapper.ObservationMapper;
import com.globant.mentorship.hotelchain.repository.IObservationRepository;
import com.globant.mentorship.hotelchain.service.IObservationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Log4j2
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
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }

        return observationContract;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfObservationAlreadyExists(ObservationContract observationContract) {
        return observationRepository.existsByTypeAndBookingEntityId(observationContract.getType(), observationContract.getBookingId());
    }

    @Override
    public List<ObservationContract> getObservationListByBookinId(Long bookingId) {
        return observationMapper.loadContractsOut(observationRepository.findAllByBookingEntityId(bookingId)
                .orElseThrow(() -> new ObservationNotFoundException("The booking has no observations")));
    }
}
