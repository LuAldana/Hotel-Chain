package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.domain.contract.out.HotelSiteContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.exception.HotelSiteNotFoundException;
import com.globant.mentorship.hotelchain.exception.RoomAlreadyExistsException;
import com.globant.mentorship.hotelchain.exception.RoomNotFoundException;
import com.globant.mentorship.hotelchain.exception.RoomTypeNotFoundException;
import com.globant.mentorship.hotelchain.service.IHotelSiteService;
import com.globant.mentorship.hotelchain.service.IRoomService;
import com.globant.mentorship.hotelchain.service.IRoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class RoomValidator {

    private final IRoomService roomService;
    private final IHotelSiteService hotelSiteService;
    private final IRoomTypeService roomTypeService;

    public Map<String, Object> createRoomValidator(RoomContract roomContract) {

        Map<String, Object> mapContractValidated = new HashMap<>();

        if(roomService.validateIfRoomAlreadyExists(roomContract))
            throw new RoomAlreadyExistsException("Room already exists");

        HotelSiteContract hotelSiteContract = hotelSiteService.getHotelSite(roomContract.getHotelSiteId());

        if(Objects.isNull(hotelSiteContract))
            throw new HotelSiteNotFoundException(String.format("Hotel site ID %s not found", roomContract.getHotelSiteId()));

        RoomTypeContract roomTypeContract = roomTypeService.getRoomType(roomContract.getRoomTypeId());

        if(Objects.isNull(roomTypeContract))
            throw new RoomTypeNotFoundException(String.format("Room type ID %d not found", roomContract.getRoomTypeId()));

        mapContractValidated.put("hotelSite", hotelSiteContract);
        mapContractValidated.put("roomType", roomTypeContract);
        mapContractValidated.put("room", roomContract);

        return mapContractValidated;
    }

    public void deleteRoomValidator(int numberRoom) {
        if(Objects.isNull(roomService.getRoom(numberRoom)))
            throw new RoomNotFoundException(String.format("Room number %d not found", numberRoom));
    }
}
