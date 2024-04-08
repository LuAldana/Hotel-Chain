package com.globant.mentorship.hotelchain.domain.contract.out;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContract {

    private Long document;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String address;
    private String phone;
    private String email;
    private String password;
    private boolean status;
    private DocumentTypeContract documentTypeContract;
    private CityContract cityContract;
    private UserTypeContract userTypeContract;
}
