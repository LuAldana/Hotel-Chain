package com.globant.mentorship.hotelchain.domain.contract.out;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeContract {

    private String name;
    private String description;
}
