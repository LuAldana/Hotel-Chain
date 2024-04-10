package com.globant.mentorship.hotelchain.service.impl;

import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.mapper.UserMapper;
import com.globant.mentorship.hotelchain.repository.IUserRepository;
import com.globant.mentorship.hotelchain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public UserContract getUserByDocument(Long document) {
        return userMapper.loadContractOut(userRepository.findByDocument(document).orElse(null));
    }

    @Override
    @Transactional
    public void blockGuest(Long document) {
        userRepository.blockGuest(document);
    }
}
