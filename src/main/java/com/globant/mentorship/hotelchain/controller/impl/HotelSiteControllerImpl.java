package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IHotelSiteController;
import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.service.IHotelSiteService;
import com.globant.mentorship.hotelchain.validator.HotelSiteValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HotelSiteControllerImpl implements IHotelSiteController {

    private final IHotelSiteService hotelSiteService;
    private final HotelSiteValidator hotelSiteValidator;

    @Override
    public ResponseEntity<List<HotelSiteContract>> getAllHotelSites() {
        return ResponseEntity.ok(hotelSiteService.getAllHotelSites());
    }

    @Override
    public ResponseEntity<HotelSiteContract> createHotelSite(HotelSiteContract hotelSiteContract) {
        Map<String, Object> mapContract = hotelSiteValidator.createHotelSiteValidator(hotelSiteContract);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(hotelSiteService.createHotelSite(mapContract));
    }

    @Override
    public ResponseEntity<Void> deleteHotelSite(Long id) {
        hotelSiteValidator.deleteHotelSiteValidator(id);
        hotelSiteService.deleteHotelSite(id);
        return ResponseEntity.noContent().build();
    }
}
