package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookingContract {

    @JsonIgnore
    private Long id;
    @NotEmpty(message = "The check in date can't be empty or null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String checkInDate;
    @NotEmpty(message = "The check out date can't be empty or null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String checkOutDate;
    @NotEmpty(message = "The check in date can't be empty or null")
    private String status;
    @Positive(message = "The room id must be greater than zero")
    private Long roomId;
    @Positive(message = "The user id must be greater than zero")
    private Long userId;
}
