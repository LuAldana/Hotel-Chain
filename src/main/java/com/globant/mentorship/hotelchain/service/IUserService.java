package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;

public interface IUserService {

    UserContract getUserByDocument(Long document);

    void blockGuest(Long document);
}
