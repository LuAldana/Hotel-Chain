package com.globant.mentorship.hotelchain.controller;

import com.globant.mentorship.hotelchain.controller.payload.BlockUserPayload;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Validated
@RequestMapping("/user")
public interface IUserController {

    @PatchMapping("/block")
    ResponseEntity<Void> blockGuest(@RequestBody @Valid BlockUserPayload blockUserPayload);
}
