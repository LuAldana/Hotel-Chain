package com.globant.mentorship.hotelchain.service;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;

public interface IRoomTypeService {

    RoomTypeContract getRoomType(Long id);

}
