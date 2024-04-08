package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.CityContract;
import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.entity.CityEntity;
import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import com.globant.mentorship.hotelchain.mapper.CityMapper;
import com.globant.mentorship.hotelchain.mapper.HotelSiteMapper;
import com.globant.mentorship.hotelchain.repository.IHotelSiteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HotelSiteServiceImplTest {

    @InjectMocks
    private HotelSiteServiceImpl hotelSiteService;
    @Mock
    private IHotelSiteRepository hotelSiteRepository;
    @Mock
    private HotelSiteMapper hotelSiteMapper;
    @Mock
    private CityMapper cityMapper;


    private HotelSiteContract hotelSiteContract;
    private CityContract cityContract;
    private HotelSiteEntity hotelSiteEntity;

    @BeforeEach
    void setUp() {

        hotelSiteContract = HotelSiteContract.builder()
                .neighborhood("TEST")
                .address("CRA. 10 #12 13")
                .phone("9876544")
                .cityCode(1L)
                .build();

        hotelSiteEntity = HotelSiteEntity.builder()
                .id(1L)
                .neighborhood("TEST")
                .address("CRA. 10 #12 13")
                .phone("9876544")
                .cityEntity(CityEntity.builder().build())
                .build();

        cityContract = CityContract.builder()
                .code(1L)
                .name("Bogotá")
                .build();

    }

    @Test
    void createHotelSiteSuccessfully() {

        // Given
        Map<String, Object> mapContract = new HashMap<>();

        mapContract.put("city", cityContract);
        mapContract.put("hotelSite", hotelSiteContract);

        when(hotelSiteMapper.loadEntityOut(hotelSiteContract)).thenReturn(hotelSiteEntity);
        when(cityMapper.loadEntityOut(cityContract)).thenReturn(CityEntity.builder().build());
        when(hotelSiteRepository.save(hotelSiteEntity)).thenReturn(hotelSiteEntity);

        // When
        HotelSiteContract hotelSiteCreated = hotelSiteService.createHotelSite(mapContract);

        // Then
        assertThat(hotelSiteCreated).isNotNull();

        verify(hotelSiteRepository, times(1)).save(any());
        verify(hotelSiteMapper, times(1)).loadEntityOut(any());

        assertThat(hotelSiteCreated.getNeighborhood()).isEqualTo(hotelSiteEntity.getNeighborhood());
        assertThat(hotelSiteCreated.getNeighborhood()).isUpperCase();
        assertThat(hotelSiteCreated.getAddress()).isEqualTo(hotelSiteEntity.getAddress());
        assertThat(hotelSiteCreated.getPhone()).isEqualTo(hotelSiteEntity.getPhone());
        assertThat(hotelSiteCreated.getCityCode()).isEqualTo(hotelSiteEntity.getCityEntity().getCode());
    }

    @Test
    void deleteHotelSite() {

        // Given
        Long hotelSiteId = 12L;
        when(hotelSiteRepository.findById(hotelSiteId)).thenReturn(Optional.of(hotelSiteEntity));

        // When
        hotelSiteService.deleteHotelSite(hotelSiteId);

        // Then
        verify(hotelSiteRepository, times(1)).delete(any());
    }

    @Test
    void getAllHotelSites() {
    }
}