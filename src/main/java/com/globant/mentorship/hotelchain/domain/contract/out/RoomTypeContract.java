package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RoomTypeContract {
    @JsonIgnore
    private Long id;
    private String name;
    private String description;
}
