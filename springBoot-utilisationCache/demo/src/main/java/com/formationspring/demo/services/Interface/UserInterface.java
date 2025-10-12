package com.formationspring.demo.services.Interface;
import org.example.dto.UserDto;
import java.util.List;

public interface UserInterface {

    List<UserDto.PostOutput> getAllUsers();
    List<UserDto.PostOutput> saveAllUsers(List<UserDto.PostInput> users);
    UserDto.PostOutput findByMailAndPassword(String mail, String password);

}
