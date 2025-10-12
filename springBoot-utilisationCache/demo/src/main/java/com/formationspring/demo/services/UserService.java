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
        List<UserDto.PostOutput> outputs = new ArrayList<>();

        for (UserDto.PostInput input : users) {
            LocalDateTime now = LocalDateTime.now();

            // Hachage du mot de passe
            String encodedPassword = passwordEncoder.encode(input.getPassword());

            // Création de l’entité
            UserEntity entity = new UserEntity();
            entity.setMail(input.getMail());
            entity.setPassword(encodedPassword);
            entity.setSearchDateTime(now);

            // Sauvegarde dans la base
            userRepositoryJpa.save(entity);

            // Retour DTO
            outputs.add(UserDto.PostOutput.builder()
                    .id(entity.getId())
                    .mail(entity.getMail())
                    //.password(entity.getPassword())
                    .searchDateTime(entity.getSearchDateTime())
                    .build());
        }

        return outputs;
    }
    @Override
    public  UserDto.PostOutput findByMailAndPassword(String mail, String password) {
        UserEntity user = userRepositoryJpa.findByMail(mail);

        if (user == null) {
            throw new RuntimeException("User not found with email: " + mail);
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return UserDto.PostOutput.builder()
                .id(user.getId())
                .mail(user.getMail())
                .searchDateTime(user.getSearchDateTime())
                .build();
    }

}
