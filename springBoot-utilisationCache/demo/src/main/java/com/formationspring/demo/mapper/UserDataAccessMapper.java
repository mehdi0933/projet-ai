package com.formationspring.demo.mapper;

import com.formationspring.demo.dto.UserDataAccessDto;

public class UserDataAccessMapper {

    public static UserDataAccessDto.PostInput fromInput(UserDataAccessDto.PostInput input) {
        return UserDataAccessDto.PostInput.builder()
                .id(null)
                .firstName(input.getFirstName())
                .lastName(input.getLastName())
                .build();
    }

    public static UserDataAccessDto.PostOutput toOutput(UserDataAccessDto.PostOutput output) {
        return UserDataAccessDto.PostOutput.builder()
                .id(output.getId())
                .firstName(output.getFirstName())
                .lastName(output.getLastName())
                .build();
    }

}
