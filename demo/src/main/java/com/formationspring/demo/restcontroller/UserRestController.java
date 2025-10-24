package com.formationspring.demo.restcontroller;

import com.formationspring.demo.jwt.JwtUtil;
import org.example.dto.UserDto;
import com.formationspring.demo.services.Interface.UserInterface;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {

    private final UserInterface userService;
    private final AuthenticationManager authManager;
    private final JwtUtil jwtUtil;

    public UserRestController(UserInterface userService, AuthenticationManager authManager, JwtUtil jwtUtil) {
        this.userService = userService;
        this.authManager = authManager;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public String login(@RequestBody UserDto.PostInput input) {
        try {

            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(input.getMail(), input.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new RuntimeException("Email ou mot de passe incorrect");
        }

        return jwtUtil.generateToken(input.getMail());
    }


    @PostMapping("/post")
    public List<UserDto.PostOutput> saveAllUsers(@RequestBody List<UserDto.PostInput> users) {
        return userService.saveAllUsers(users);
    }

    @GetMapping("/test/permisAll")
    public String permisAll() {
        return "je suis le test permisAll";
    }

    @GetMapping("/test/security")
    public String security() {
        return "je suis le test security ";
    }
}
