package com.globant.mentorship.hotelchain.controller.impl;

import com.globant.mentorship.hotelchain.controller.IUserController;
import com.globant.mentorship.hotelchain.service.IUserService;
import com.globant.mentorship.hotelchain.validator.UserValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements IUserController {

    private final IUserService userService;
    private final UserValidator userValidator;

    @Override
    public ResponseEntity<Void> blockGuest(String document) {
        userValidator.blockGuestValidator(document);
        userService.blockGuest(Long.valueOf(document));
        return ResponseEntity.ok().build();
    }
}
