package com.globant.mentorship.hotelchain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public interface IUserController {

    @GetMapping("/users")
    ResponseEntity<String> test();
}
