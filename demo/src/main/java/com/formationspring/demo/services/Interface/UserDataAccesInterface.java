package com.formationspring.demo.services.Interface;
import com.formationspring.demo.dto.UserDataAccessDto;
import java.util.List;

public interface UserDataAccesInterface {

    List<UserDataAccessDto.PostOutput> getAllUsers();
    List<UserDataAccessDto.PostOutput> saveAllUsers(List<UserDataAccessDto.PostInput> users);
}
