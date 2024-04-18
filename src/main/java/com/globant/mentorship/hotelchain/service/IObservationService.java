package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;

import java.util.List;
import java.util.Map;

public interface IObservationService {

    ObservationContract createObservation(Map<String, Object> mapContractValidated);

    boolean validateIfObservationAlreadyExists(ObservationContract observationContract);

    List<ObservationContract> getObservationListByBookinId(Long bookingId);
}
