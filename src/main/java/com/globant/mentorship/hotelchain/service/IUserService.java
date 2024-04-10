package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;

public interface IUserService {

    UserContract getUserByDocument(Long document);

    UserContract getUserById(Long id);

    void blockGuest(Long document);
}
