package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;
import com.globant.mentorship.hotelchain.mapper.CityMapper;
import com.globant.mentorship.hotelchain.repository.ICityRepository;
import com.globant.mentorship.hotelchain.service.ICityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CityServiceImpl implements ICityService {

    private final ICityRepository cityRepository;
    private final CityMapper cityMapper;

    public CityContract getCity(Long code) {
        return cityMapper.loadContractOut(cityRepository.findByCode(code).orElse(null));
    }

}
