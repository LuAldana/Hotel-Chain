package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;

import java.util.Map;

public interface IRoomService {

    RoomContract createRoom(Map<String, Object> mapContractValidated);

    void deleteRoom(int numberRoom);

    boolean validateIfRoomAlreadyExists(RoomContract roomContract);

    RoomContract getRoomByNumber(int number);

    RoomContract getRoomById(Long id);

    RoomTypeContract getRoomTypeMostBooked();
}
