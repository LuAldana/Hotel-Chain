package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IRoomController;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.service.IRoomService;
import com.globant.mentorship.hotelchain.validator.RoomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class RoomControllerImpl implements IRoomController {

    private final IRoomService roomService;
    private final RoomValidator roomValidator;

    @Override
    public ResponseEntity<RoomTypeContract> getRoomTypeMostBooked() {
        return ResponseEntity.ok(roomService.getRoomTypeMostBooked());
    }

    @Override
    public ResponseEntity<RoomContract> createRoom(RoomContract roomContract) {
        Map<String, Object> mapContractValidated = roomValidator.createRoomValidator(roomContract);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(roomService.createRoom(mapContractValidated));
    }

    @Override
    public ResponseEntity<Void> deleteRoom(String numberRoom) {
        roomValidator.deleteRoomValidator(numberRoom);
        roomService.deleteRoom(Integer.parseInt(numberRoom));
        return ResponseEntity.noContent().build();
    }
}
