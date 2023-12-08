package com.crcl.iam.dto;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class CreateAccount {

    @Valid
    private UserInformation userInformation;
    @Valid
    private ContactInformation contactInformation;
    @Valid
    private LocationInformation locationInformation;
    @Valid
    private SignUpInformation signUpInformation;

    private int step = 1;

    public UserDto toUserDto() {
        return new UserDto()
                .setFirstName(userInformation.getFirstName())
                .setLastName(userInformation.getLastName())
                .setBirthDate(userInformation.getBirthDate())
                .setUserName(signUpInformation.getUsername())
                .setPassword(signUpInformation.getPassword())
                .setConfirmPassword(signUpInformation.getConfirmPassword())
                .setEmail(contactInformation.getEmail())
                .setPhone(contactInformation.getPhone());
    }
}
