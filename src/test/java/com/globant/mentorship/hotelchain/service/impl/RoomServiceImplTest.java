package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import com.globant.mentorship.hotelchain.domain.entity.RoomEntity;
import com.globant.mentorship.hotelchain.domain.entity.RoomTypeEntity;
import com.globant.mentorship.hotelchain.mapper.HotelSiteMapper;
import com.globant.mentorship.hotelchain.mapper.RoomMapper;
import com.globant.mentorship.hotelchain.mapper.RoomTypeMapper;
import com.globant.mentorship.hotelchain.repository.IRoomRepository;
import com.globant.mentorship.hotelchain.repository.IRoomTypeRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoomServiceImplTest {

    @InjectMocks
    RoomServiceImpl roomService;
    @Mock
    IRoomRepository roomRepository;
    @Mock
    IRoomTypeRepository roomTypeRepository;
    @Mock
    RoomMapper roomMapper;
    @Mock
    HotelSiteMapper hotelSiteMapper;
    @Mock
    RoomTypeMapper roomTypeMapper;

    private RoomContract roomContract;
    private RoomEntity roomEntity;
    private HotelSiteContract hotelSiteContract;
    private RoomTypeContract roomTypeContract;

    @BeforeEach
    void setUp() {
        roomContract = RoomContract.builder()
                .number(805)
                .hasPrivateBath(true)
                .hasPhone(true)
                .hasHeating(false)
                .status("AVAILABLE")
                .hotelSiteId(5L)
                .roomTypeId(6L)
                .build();

        roomEntity = RoomEntity.builder()
                .id(2L)
                .number(805)
                .hasPrivateBath(true)
                .hasPhone(true)
                .hasHeating(false)
                .status("AVAILABLE")
                .hotelSiteEntity(HotelSiteEntity.builder().build())
                .roomTypeEntity(RoomTypeEntity.builder().build())
                .build();

        hotelSiteContract = HotelSiteContract.builder()
                .neighborhood("Grove Street")
                .address("CRA. 123")
                .phone("1234567")
                .cityCode(1L)
                .build();

        roomTypeContract = RoomTypeContract.builder()
                .name("Simple")
                .description("Simple room")
                .build();
    }

    @Test
    void createRoomSuccessfully() {

        // Given
        Map<String, Object> mapContract = new HashMap<>();

        mapContract.put("roomType", roomTypeContract);
        mapContract.put("hotelSite", hotelSiteContract);
        mapContract.put("room", roomContract);

        when(hotelSiteMapper.loadEntityOut(hotelSiteContract)).thenReturn(HotelSiteEntity.builder().build());
        when(roomTypeMapper.loadEntityOut(roomTypeContract)).thenReturn(RoomTypeEntity.builder().build());
        when(roomMapper.loadEntityOut(roomContract)).thenReturn(roomEntity);
        when(roomRepository.save(roomEntity)).thenReturn(roomEntity);

        // When
        RoomContract savedRoom = roomService.createRoom(mapContract);

        // Then
        assertThat(savedRoom).isNotNull();

        verify(roomMapper, times(1)).loadEntityOut(any());
        verify(roomRepository, times(1)).save(any());

        assertThat(savedRoom.getNumber()).isEqualTo(roomEntity.getNumber());
        assertThat(savedRoom.getStatus()).isEqualTo(roomEntity.getStatus());
        assertThat(savedRoom.getHotelSiteId()).isEqualTo(roomEntity.getHotelSiteEntity().getId());
        assertThat(savedRoom.getRoomTypeId()).isEqualTo(roomEntity.getRoomTypeEntity().getId());

    }


    @Test
    void deleteRoomSuccessfully() {

        // Given
        when(roomRepository.findByNumber(roomContract.getNumber())).thenReturn(Optional.of(roomEntity));

        // When
        roomService.deleteRoom(roomContract.getNumber());

        // Then
        verify(roomRepository, times(1)).delete(any());
    }

    @Test
    void getRoomTypeMostBookedSuccessfully() {

        // Given
        Long roomTypeId = 2L;
        when(roomRepository.findTypeMostBooked()).thenReturn(Optional.of(roomTypeId));
        when(roomTypeRepository.findById(anyLong())).thenReturn(Optional.of(RoomTypeEntity.builder().id(roomTypeId).build()));
        when(roomTypeMapper.loadContractOut(any())).thenReturn(RoomTypeContract.builder().build());

        // When
        RoomTypeContract roomTypeContractFound = roomService.getRoomTypeMostBooked();

        // Then
        assertThat(roomTypeContractFound).isNotNull();

    }

}