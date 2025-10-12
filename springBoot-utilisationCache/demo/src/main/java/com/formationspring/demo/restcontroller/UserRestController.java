package com.formationspring.demo.restcontroller;

import org.example.dto.UserDto;
import com.formationspring.demo.services.Interface.UserInterface;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserInterface userService;

    public UserRestController(UserInterface userService) {
        this.userService = userService;
    }

    @PostMapping("/log")
    public UserDto.PostOutput login(@RequestBody UserDto.PostInput input) {
        return userService.findByMailAndPassword(input.getMail(), input.getPassword());
    }

    @PostMapping("/post")
    public List<UserDto.PostOutput> saveAllUsers(@RequestBody List<UserDto.PostInput> users) {
        return userService.saveAllUsers(users);
    }
}
