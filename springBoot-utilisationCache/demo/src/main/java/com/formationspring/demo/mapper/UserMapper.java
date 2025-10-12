package com.formationspring.demo.mapper;

import org.example.dto.UserDto;

public class UserMapper {

    public static UserDto.PostInput fromInput(UserDto.PostInput input) {
        return UserDto.PostInput.builder()
                .id(null)
                .mail(input.getMail())
                .password(input.getPassword())
                .searchDateTime(input.getSearchDateTime())
                .build();
    }

    public static UserDto.PostOutput toOutput(UserDto.PostOutput output) {
        return UserDto.PostOutput.builder()
                .id(output.getId())
                .mail(output.getMail())
                //.password(output.getPassword())
                .searchDateTime(output.getSearchDateTime())
                .build();
    }
}

