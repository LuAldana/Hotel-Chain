package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IUserController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserControllerImpl implements IUserController {

    @Override
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("The best hotel chain of my neighborhood");
    }
}
