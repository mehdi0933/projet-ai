package com.formationspring.demo.services;

import com.formationspring.demo.dal.RoleRepositoryJpa;
import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.entity.RoleEntity;
import com.formationspring.demo.entity.UserEntity;
import com.formationspring.demo.services.contracts.UserInterface;
import lombok.RequiredArgsConstructor;
import org.example.dto.RegisterUserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private final UserRepositoryJpa userRepositoryJpa;
    private final RoleRepositoryJpa roleRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<RegisterUserDto.PostOutput> saveAllUsers(List<RegisterUserDto.PostInput> users) {

        List<UserEntity> existingUsers = userRepositoryJpa.findAllByMailIn(
                users.stream().map(RegisterUserDto.PostInput::getMail).toList()
        );

        Map<String, UserEntity> existingMails = existingUsers.stream()
                .collect(Collectors.toMap(UserEntity::getMail, u -> u, (existing, replacement) -> existing));

        return users.stream().map(input -> {
            UserEntity user;
            if (existingMails.containsKey(input.getMail())) {
                user = existingMails.get(input.getMail());
            } else {
                user = new UserEntity();
                user.setMail(input.getMail());
                user.setPassword(passwordEncoder.encode(input.getPassword()));
                user.setSearchDateTime(LocalDateTime.now());
                user.setRoles(new HashSet<>());
            }

            // Ajouter les rôles
            if (input.getRoles() != null) {
                Set<RoleEntity> roles = input.getRoles().stream()
                        .map(roleName -> roleRepositoryJpa.findAllByName(roleName).stream().findFirst()
                                .orElseGet(() -> roleRepositoryJpa.save(RoleEntity.builder().name(roleName).build())))
                        .collect(Collectors.toSet());
                user.setRoles(roles);
            }

            userRepositoryJpa.save(user);

            return RegisterUserDto.PostOutput.builder()
                    .id(user.getId())
                    .mail(user.getMail())
                    .roles(user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                    .build();
        }).toList();
    }


    @Override
    public UserEntity findByMail(String mail) {
        UserEntity user = userRepositoryJpa.findByMail(mail);
        if (user == null) {
            throw new RuntimeException("User not found with email: " + mail);
        }
        return user;
    }

    @Override
    public List<RegisterUserDto.PostOutput> getAllUsers() {
        List<UserEntity> users = userRepositoryJpa.findAll();
        return users.stream().map(user ->
                RegisterUserDto.PostOutput.builder()
                        .id(user.getId())
                        .mail(user.getMail())
                        .roles(user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                        .searchDateTime(user.getSearchDateTime())
                        .build()
        ).toList();
    }

    @Override
    public RegisterUserDto.PostOutput findByMailAndPassword(String mail, String password) {
        UserEntity user = userRepositoryJpa.findByMail(mail);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + mail);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password for email: " + mail);
        }

        return RegisterUserDto.PostOutput.builder()
                .id(user.getId())
                .mail(user.getMail())
                .roles(user.getRoles().stream().map(RoleEntity::getName).collect(Collectors.toSet()))
                .searchDateTime(user.getSearchDateTime())
                .build();
    }
}