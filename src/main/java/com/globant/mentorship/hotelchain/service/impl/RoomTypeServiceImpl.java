package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.RoomTypeContract;
import com.globant.mentorship.hotelchain.mapper.RoomTypeMapper;
import com.globant.mentorship.hotelchain.repository.IRoomTypeRepository;
import com.globant.mentorship.hotelchain.service.IRoomTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RoomTypeServiceImpl implements IRoomTypeService {

    private final IRoomTypeRepository roomTypeRepository;
    private final RoomTypeMapper roomTypeMapper;

    @Override
    public RoomTypeContract getRoomType(Long id) {
        return roomTypeMapper.loadContractOut(roomTypeRepository.findById(id).orElse(null));
    }
}
