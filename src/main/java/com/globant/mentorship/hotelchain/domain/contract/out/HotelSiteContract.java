package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HotelSiteContract {

    @JsonIgnore
    private Long id;
    @NotEmpty(message = "Neighborhood mustn't be null or empty")
    private String neighborhood;
    @NotEmpty(message = "Address mustn't be null or empty")
    private String address;
    @NotNull(message = "Phone can't be null")
    @Positive(message = "Phone must be greater than zero")
    @Size(min = 7, max = 12, message = "Phone must be at least 7 digits in length")
    @Pattern(regexp = "\\d+", message = "Phone number must have only numeric values")
    private String phone;
    @NotNull(message = "City code mustn't be null or empty")
    @Positive(message = "City code must be greater than zero")
    private Long cityCode;
}
