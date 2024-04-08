package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomContract;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/room")
public interface IRoomController {

    @PostMapping
    ResponseEntity<RoomContract> createRoom(@Valid @RequestBody RoomContract roomContract);

    @DeleteMapping("/{numberRoom}")
    ResponseEntity<Void> deleteRoom(@PathVariable int numberRoom);
}
