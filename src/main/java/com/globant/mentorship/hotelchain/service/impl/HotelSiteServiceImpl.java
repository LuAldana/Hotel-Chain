package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;
import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.mapper.CityMapper;
import com.globant.mentorship.hotelchain.mapper.HotelSiteMapper;
import com.globant.mentorship.hotelchain.repository.IHotelSiteRepository;
import com.globant.mentorship.hotelchain.service.IHotelSiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class HotelSiteServiceImpl implements IHotelSiteService {

    private final IHotelSiteRepository hotelSiteRepository;
    private final HotelSiteMapper hotelSiteMapper;
    private final CityMapper cityMapper;

    @Override
    @Transactional(readOnly = true)
    public List<HotelSiteContract> getAllHotelSites() {
        return hotelSiteMapper.loadContractsOut(hotelSiteRepository.findAll());
    }

    @Override
    @Transactional
    public HotelSiteContract createHotelSite(Map<String, Object> mapContract) {

        HotelSiteContract hotelSiteContract = (HotelSiteContract) mapContract.get("hotelSite");
        CityContract cityContract = (CityContract) mapContract.get("city");

        HotelSiteEntity hotelSiteEntity = hotelSiteMapper.loadEntityOut(hotelSiteContract);
        hotelSiteEntity.setCityEntity(cityMapper.loadEntityOut(cityContract));

        try {
            hotelSiteRepository.save(hotelSiteEntity);
        } catch (Exception e) {
            throw new GenericServerError(e.getLocalizedMessage(), e.getCause());
        }

        return hotelSiteContract;
    }

    @Override
    @Transactional
    public void deleteHotelSite(Long id) {
        hotelSiteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public HotelSiteContract getHotelSite(Long id) {
        return hotelSiteMapper.loadContractOut(hotelSiteRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfHotelSiteAlreadyExists(HotelSiteContract hotelSiteContract) {
        return hotelSiteRepository.findByNeighborhood(hotelSiteContract.getNeighborhood()).isPresent();
    }
}
