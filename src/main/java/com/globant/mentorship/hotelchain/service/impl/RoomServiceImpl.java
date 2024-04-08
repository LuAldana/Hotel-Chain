package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.domain.entity.HotelSiteEntity;
import com.globant.mentorship.hotelchain.domain.entity.RoomEntity;
import com.globant.mentorship.hotelchain.domain.entity.RoomTypeEntity;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.mapper.HotelSiteMapper;
import com.globant.mentorship.hotelchain.mapper.RoomMapper;
import com.globant.mentorship.hotelchain.mapper.RoomTypeMapper;
import com.globant.mentorship.hotelchain.repository.IRoomRepository;
import com.globant.mentorship.hotelchain.service.IRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    private final IRoomRepository roomRepository;
    private final RoomMapper roomMapper;
    private final HotelSiteMapper hotelSiteMapper;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    @Transactional
    public RoomContract createRoom(Map<String, Object> mapContract) {

        RoomContract roomContract = (RoomContract) mapContract.get("room");
        HotelSiteContract hotelSiteContract = (HotelSiteContract) mapContract.get("hotelSite");
        RoomTypeContract roomTypeContract = (RoomTypeContract) mapContract.get("roomType");

        HotelSiteEntity hotelSiteEntity = hotelSiteMapper.loadEntityOut(hotelSiteContract);
        hotelSiteEntity.setId(roomContract.getHotelSiteId());

        RoomTypeEntity roomTypeEntity = roomTypeMapper.loadEntityOut(roomTypeContract);
        roomTypeEntity.setId(roomContract.getRoomTypeId());

        RoomEntity roomEntity = roomMapper.loadEntityOut(roomContract);
        roomEntity.setHotelSiteEntity(hotelSiteEntity);
        roomEntity.setRoomTypeEntity(roomTypeEntity);

        try {
            roomRepository.save(roomEntity);
        } catch (Exception e) {
            throw new GenericServerError(e.getLocalizedMessage(), e.getCause());
        }

        return roomContract;
    }

    @Override
    public void deleteRoom(int numberRoom) {
        RoomEntity roomEntity = getRoom(numberRoom);
        roomRepository.delete(roomEntity);
    }

    @Override
    public boolean validateIfRoomAlreadyExists(RoomContract roomContract) {
        return roomRepository.findByNumber(roomContract.getNumber()).isPresent();
    }

    @Override
    public RoomEntity getRoom(int number) {
        return roomRepository.findByNumber(number).orElse(null);
    }

}
