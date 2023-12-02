package com.crcl.iam.dto;

import lombok.Data;

@Data
public class CreateAccount {

    private UserInformation userInformation;
    private ContactInformation contactInformation;
    private LocationInformation locationInformation;
    private SignUpInformation signUpInformation;

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
