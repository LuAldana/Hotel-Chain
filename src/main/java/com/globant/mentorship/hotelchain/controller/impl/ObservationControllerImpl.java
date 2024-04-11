package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IObservationController;
import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import com.globant.mentorship.hotelchain.service.IObservationService;
import com.globant.mentorship.hotelchain.validator.ObservationValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ObservationControllerImpl implements IObservationController {

    private final IObservationService observationService;
    private final ObservationValidator observationValidator;

    @Override
    public ResponseEntity<ObservationContract> createObservation(ObservationContract observationContract) {
        Map<String, Object> mapContractValidated = observationValidator.createObservationValidator(observationContract);
        return ResponseEntity.status(HttpStatus.CREATED).body(observationService.createObservation(mapContractValidated));
    }
}
