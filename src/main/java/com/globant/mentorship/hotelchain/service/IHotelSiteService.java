package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;

import java.util.List;
import java.util.Map;

public interface IHotelSiteService {

    HotelSiteContract createHotelSite(Map<String, Object> mapContract);
    List<HotelSiteContract> getAllHotelSites();
    void deleteHotelSite(Long id);
    HotelSiteContract getHotelSite(Long id);
    boolean validateIfHotelSiteAlreadyExists(HotelSiteContract hotelSiteContract);
}
