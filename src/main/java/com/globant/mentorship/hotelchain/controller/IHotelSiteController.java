package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RequestMapping("/hotelsite")
public interface IHotelSiteController {

    @GetMapping
    ResponseEntity<List<HotelSiteContract>> getAllHotelSites();

    @PostMapping
    ResponseEntity<HotelSiteContract> createHotelSite(@Valid @RequestBody HotelSiteContract hotelSiteContract);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteHotelSite(@PathVariable Long id);
}
