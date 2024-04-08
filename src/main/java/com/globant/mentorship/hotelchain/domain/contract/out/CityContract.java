package com.globant.mentorship.hotelchain.domain.contract.out;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityContract {
    private Long code;
    private String name;
}
