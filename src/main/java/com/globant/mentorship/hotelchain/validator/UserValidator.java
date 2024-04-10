package com.globant.mentorship.hotelchain.validator;

import com.globant.mentorship.hotelchain.domain.contract.out.UserContract;
import com.globant.mentorship.hotelchain.exception.UserNotFoundException;
import com.globant.mentorship.hotelchain.exception.UserValidatorException;
import com.globant.mentorship.hotelchain.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@RequiredArgsConstructor
@Component
public class UserValidator {

    private final IUserService userService;

    public void blockGuestValidator(String document) {

        if(!StringUtils.trimAllWhitespace(String.valueOf(document)).matches("\\d+"))
            throw new UserValidatorException("Document is not valid");

        UserContract userContract = userService.getUserByDocument(Long.valueOf(document));

        if(Objects.isNull(userContract))
            throw new UserNotFoundException(String.format("User document %s not found", document));

        if(userContract.isStatus())
            throw new UserValidatorException("Guest is already blocked");
    }
}
