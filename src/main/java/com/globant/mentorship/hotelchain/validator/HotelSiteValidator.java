package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;
import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.exception.CityNotFoundException;
import com.globant.mentorship.hotelchain.exception.HotelSiteAlreadyExistsException;
import com.globant.mentorship.hotelchain.exception.HotelSiteNotFoundException;
import com.globant.mentorship.hotelchain.service.ICityService;
import com.globant.mentorship.hotelchain.service.IHotelSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class HotelSiteValidator {

    private final IHotelSiteService hotelSiteService;
    private final ICityService cityService;

    public Map<String, Object> createHotelSiteValidator(HotelSiteContract hotelSiteContract) {

        Map<String, Object> mapContractValidated = new HashMap<>();

        if(hotelSiteService.validateIfHotelSiteAlreadyExists(hotelSiteContract))
            throw new HotelSiteAlreadyExistsException("Hotel site already exists");

        CityContract cityContract = cityService.getCity(hotelSiteContract.getCityCode());

        if(Objects.isNull(cityContract))
            throw new CityNotFoundException(String.format("City code %d not found", hotelSiteContract.getCityCode()));

        mapContractValidated.put("city", cityContract);
        mapContractValidated.put("hotelSite", hotelSiteContract);

        return mapContractValidated;
    }

    public void deleteHotelSiteValidator(Long id) {
        if(Objects.isNull(hotelSiteService.getHotelSite(id)))
            throw new HotelSiteNotFoundException(String.format("Hotel site ID %s not found", id));
    }
}
