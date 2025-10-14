package com.formationspring.demo.services;

import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.entity.UserEntity;
import com.formationspring.demo.services.Interface.UserInterface;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService implements UserInterface {

    private final UserRepositoryJpa userRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto.PostOutput> getAllUsers() {
        List<UserEntity> users = userRepositoryJpa.findAll();
        List<UserDto.PostOutput> outputs = new ArrayList<>();

        for (UserEntity user : users) {
            UserDto.PostOutput dto = UserDto.PostOutput.builder()
                    .id(user.getId())
                    .mail(user.getMail())
                    .searchDateTime(user.getSearchDateTime())
                    .build();

            outputs.add(dto);
        }

        return outputs;
    }

    @Override
    public List<UserDto.PostOutput> saveAllUsers(List<UserDto.PostInput> users) {

        List<String> mailsToCheck = users.stream()
                .map(UserDto.PostInput::getMail)
                .toList();

        List<String> existingMails = userRepositoryJpa.findAllByMailIn(mailsToCheck).stream()
                .map(UserEntity::getMail)
                .toList();

        return users.stream().map(input -> {
            if (existingMails.contains(input.getMail())) {
                return UserDto.PostOutput.builder()
                        .mail(input.getMail())
                        .build();
            } else {
                UserEntity entity = new UserEntity();
                entity.setMail(input.getMail());
                entity.setPassword(passwordEncoder.encode(input.getPassword()));
                entity.setSearchDateTime(LocalDateTime.now());
                userRepositoryJpa.save(entity);

                return UserDto.PostOutput.builder()
                        .id(entity.getId())
                        .mail(entity.getMail())
                        .searchDateTime(entity.getSearchDateTime())
                        .build();
            }
        }).toList();
    }

    @Override
    public  UserDto.PostOutput findByMailAndPassword(String mail, String password) {
        UserEntity user = userRepositoryJpa.findByMail(mail);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + mail);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password for email: " + mail);
        }

        return UserDto.PostOutput.builder()
                .id(user.getId())
                .mail(user.getMail())
                .searchDateTime(user.getSearchDateTime())
                .build();
    }

}
