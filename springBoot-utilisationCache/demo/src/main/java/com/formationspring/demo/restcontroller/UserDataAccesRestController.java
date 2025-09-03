package com.formationspring.demo.restcontroller;

import com.formationspring.demo.dto.UserDataAccessDto;
import com.formationspring.demo.services.Interface.UserDataAccesInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class UserDataAccesRestController {

    private final UserDataAccesInterface userService;

    public UserDataAccesRestController(@Qualifier("userDataAccesService")UserDataAccesInterface userService) {

        this.userService = userService;
    }

    @GetMapping("users/get")
    public List<UserDataAccessDto.PostOutput> getAllUsers() {

        return userService.getAllUsers();
    }


    @PostMapping("users/post")
    public List<UserDataAccessDto.PostOutput> saveAllUsers(@RequestBody List<UserDataAccessDto.PostInput> users) {
        return userService.saveAllUsers(users);
    }


}

