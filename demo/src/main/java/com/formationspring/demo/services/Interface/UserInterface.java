package com.formationspring.demo.services.contracts;

import org.example.dto.RegisterUserDto;
import com.formationspring.demo.entity.UserEntity;
import java.util.List;

public interface UserInterface {
    List<RegisterUserDto.PostOutput> getAllUsers();
    List<RegisterUserDto.PostOutput> saveAllUsers(List<RegisterUserDto.PostInput> users);
    RegisterUserDto.PostOutput findByMailAndPassword(String mail, String password);
    UserEntity findByMail(String mail);
}
