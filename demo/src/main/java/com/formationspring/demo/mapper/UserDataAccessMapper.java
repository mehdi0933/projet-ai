package com.formationspring.demo.mapper;

import org.example.dto.UserDataAccessDto;

public class UserDataAccessMapper {

    public static UserDataAccessDto.PostInput fromInput(UserDataAccessDto.PostInput input) {
        return UserDataAccessDto.PostInput.builder()
                .id(null)
                .mail(input.getMail())
                .password(input.getPassword())
                .searchDateTime(input.getSearchDateTime())
                .build();
    }

    public static UserDataAccessDto.PostOutput toOutput(UserDataAccessDto.PostOutput output) {
        return UserDataAccessDto.PostOutput.builder()
                .id(output.getId())
                .mail(output.getMail())
                .password(output.getPassword())
                .searchDateTime(output.getSearchDateTime())
                .build();
    }
}

