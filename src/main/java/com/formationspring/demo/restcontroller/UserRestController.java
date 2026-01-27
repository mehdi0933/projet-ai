package com.formationspring.demo.restcontroller;

import com.formationspring.demo.entity.RoleEntity;
import com.formationspring.demo.entity.UserEntity;
import com.formationspring.demo.jwt.JwtUtil;
import com.formationspring.demo.services.Interface.UserInterface;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import org.example.dto.RegisterUserDto;
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
  public String login(@RequestBody RegisterUserDto.PostInput input) {
    try {
      authManager.authenticate(
          new UsernamePasswordAuthenticationToken(input.getMail(), input.getPassword())
      );
    } catch (BadCredentialsException e) {
      throw new RuntimeException("Email ou mot de passe incorrect");
    }

    UserEntity user = userService.findByMail(input.getMail());
    Set<String> roles = user.getRoles().stream()
        .map(RoleEntity::getName)
        .collect(Collectors.toSet());

    return jwtUtil.generateToken(user.getMail(), roles);
  }


  @PostMapping("/post")
  public List<RegisterUserDto.PostOutput> saveAllUsers(@RequestBody List<RegisterUserDto.PostInput> users) {
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

  @GetMapping("/admin/dashboard")
  public String adminDashboard() {
    return "Bienvenue sur le dashboard ADMIN";
  }

}
