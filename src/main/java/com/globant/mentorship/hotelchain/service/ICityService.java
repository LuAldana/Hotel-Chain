package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;

public interface ICityService {

    CityContract getCity(Long code);
}
