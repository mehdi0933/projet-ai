package com.formationspring.demo.restcontroller;

import org.example.dto.UserDataAccessDto;
import com.formationspring.demo.services.Interface.UserDataAccesInterface;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/")
public class UserDataAccesRestController {

    private final UserDataAccesInterface userService;

    public UserDataAccesRestController(@Qualifier("userDataAccessService") UserDataAccesInterface userService) {
        this.userService = userService;
    }

    @PostMapping("users/post")
    public List<UserDataAccessDto.PostOutput> saveAllUsers(@RequestBody List<UserDataAccessDto.PostInput> users) {
        return userService.saveAllUsers(users);
    }
}
