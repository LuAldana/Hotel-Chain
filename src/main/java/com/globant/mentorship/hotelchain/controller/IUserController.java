package com.globant.mentorship.hotelchain.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/user")
public interface IUserController {

    @PatchMapping("/admin/block")
    ResponseEntity<Void> blockGuest(@RequestParam("document") String document);
}
