package com.globant.mentorship.hotelchain.domain.contract.out;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserContract {

    @JsonIgnore
    private Long id;

    @NotEmpty(message = "Document mustn't be null or empty")
    private String document;

    @NotEmpty(message = "Name mustn't be null or empty")
    private String name;

    @NotEmpty(message = "First last name mustn't be null or empty")
    private String firstLastName;

    @NotEmpty(message = "Second last name mustn't be null or empty")
    private String secondLastName;

    @NotEmpty(message = "Address mustn't be null or empty")
    private String address;

    @NotEmpty(message = "Neighborhood mustn't be null or empty")
    @Positive(message = "Phone must be greater than zero")
    @Pattern(regexp = "\\d+", message = "Phone number must have only positive numeric values")
    @Size(min = 7, max = 12, message = "Phone must be at least 7 digits in length")
    private String phone;

    @Email(message = "The e-mail format is not valid")
    private String email;

    @NotEmpty(message = "Password mustn't be null or empty")
    private String password;

    @NotNull(message = "You must indicate whether the user is blocked or not")
    private boolean status;

    @NotNull(message = "Document type id mustn't be null or empty")
    @Positive(message = "Document type id  must be greater than zero")
    private Long documentTypeId;

    @NotNull(message = "City code mustn't be null or empty")
    @Positive(message = "City code  must be greater than zero")
    private Long cityCode;

    @NotNull(message = "User type id mustn't be null or empty")
    @Positive(message = "User type id  must be greater than zero")
    private Long userTypeId;
}
