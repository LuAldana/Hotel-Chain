package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.domain.entity.RoomEntity;
import com.globant.mentorship.hotelchain.domain.entity.RoomTypeEntity;
import com.globant.mentorship.hotelchain.domain.enumeration.RoomStatusEnum;
import com.globant.mentorship.hotelchain.exception.GenericServerError;
import com.globant.mentorship.hotelchain.exception.RoomTypeNotFoundException;
import com.globant.mentorship.hotelchain.mapper.HotelSiteMapper;
import com.globant.mentorship.hotelchain.mapper.RoomMapper;
import com.globant.mentorship.hotelchain.mapper.RoomTypeMapper;
import com.globant.mentorship.hotelchain.repository.IRoomRepository;
import com.globant.mentorship.hotelchain.repository.IRoomTypeRepository;
import com.globant.mentorship.hotelchain.service.IRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Log4j2
@RequiredArgsConstructor
public class RoomServiceImpl implements IRoomService {

    private final IRoomRepository roomRepository;
    private final IRoomTypeRepository roomTypeRepository;
    private final RoomMapper roomMapper;
    private final HotelSiteMapper hotelSiteMapper;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    @Transactional
    public RoomContract createRoom(Map<String, Object> mapContract) {

        RoomContract roomContract = (RoomContract) mapContract.get("room");
        HotelSiteContract hotelSiteContract = (HotelSiteContract) mapContract.get("hotelSite");
        RoomTypeContract roomTypeContract = (RoomTypeContract) mapContract.get("roomType");

        RoomEntity roomEntity = roomMapper.loadEntityOut(roomContract);
        roomEntity.setHotelSiteEntity(hotelSiteMapper.loadEntityOut(hotelSiteContract));
        roomEntity.setRoomTypeEntity(roomTypeMapper.loadEntityOut(roomTypeContract));

        try {
            roomRepository.save(roomEntity);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }

        return roomContract;
    }

    @Override
    @Transactional
    public void deleteRoom(int numberRoom) {
        roomRepository.deleteByNumber(numberRoom);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateIfRoomAlreadyExists(RoomContract roomContract) {
        return roomRepository.findByNumber(roomContract.getNumber()).isPresent();
    }

    @Override
    @Transactional(readOnly = true)
    public RoomContract getRoomByNumber(int number) {
        return roomMapper.loadContractOut(roomRepository.findByNumber(number).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public RoomContract getRoomById(Long id) {
        return roomMapper.loadContractOut(roomRepository.findById(id).orElse(null));
    }

    @Override
    @Transactional(readOnly = true)
    public RoomTypeContract getRoomTypeMostBooked() {

        Long roomTypeId = roomRepository.findTypeMostBooked()
                .orElseThrow(() -> new RoomTypeNotFoundException("No type of rooms found"));

        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(roomTypeId)
                .orElseThrow(() -> new RoomTypeNotFoundException(String.format("Room type ID %d not found", roomTypeId)));

        return roomTypeMapper.loadContractOut(roomTypeEntity);
    }

    @Override
    public void setStatusToBooked(Long roomId) {

        RoomEntity roomEntity = roomRepository.findById(roomId).get();
        roomEntity.setStatus(RoomStatusEnum.BOOKED.getDescription());

        try {
            roomRepository.save(roomEntity);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }

    }

    @Override
    public void setStatusToAvailable(Long roomId) {
        RoomEntity roomEntity = roomRepository.findById(roomId).get();
        roomEntity.setStatus(RoomStatusEnum.AVAILABLE.getDescription());

        try {
            roomRepository.save(roomEntity);
        } catch (DataAccessException e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Error when saving in the repository", e.getCause());

        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e.getCause());
            throw new GenericServerError("Generic error", e.getCause());
        }
    }

}
