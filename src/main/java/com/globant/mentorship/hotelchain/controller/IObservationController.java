package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.contract.out.ObservationContract;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/observation")
public interface IObservationController {

    @PostMapping
    ResponseEntity<ObservationContract> createObservation(@RequestBody @Valid ObservationContract observationContract);
}
