package com.formationspring.demo.services;

import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.entity.UserDataAccesEntity;
import com.formationspring.demo.services.Interface.UserDataAccesInterface;
import lombok.RequiredArgsConstructor;
import org.example.dto.UserDataAccessDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service("userDataAccessService")
@RequiredArgsConstructor
public class UserDataAccessService implements UserDataAccesInterface {

    private final UserRepositoryJpa userRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDataAccessDto.PostOutput> getAllUsers() {
        List<UserDataAccesEntity> users = userRepositoryJpa.findAll();
        List<UserDataAccessDto.PostOutput> outputs = new ArrayList<>();

        for (UserDataAccesEntity user : users) {
            UserDataAccessDto.PostOutput dto = UserDataAccessDto.PostOutput.builder()
                    .id(user.getId())
                    .mail(user.getMail())
                    .searchDateTime(user.getSearchDateTime())
                    .build();

            outputs.add(dto);
        }

        return outputs;
    }


    @Override
    public List<UserDataAccessDto.PostOutput> saveAllUsers(List<UserDataAccessDto.PostInput> users) {
        List<UserDataAccessDto.PostOutput> outputs = new ArrayList<>();

        for (UserDataAccessDto.PostInput input : users) {
            LocalDateTime now = LocalDateTime.now();

            // Encodage du mot de passe
            String encodedPassword = passwordEncoder.encode(input.getPassword());

            // Création de l’entité
            UserDataAccesEntity entity = new UserDataAccesEntity();
            entity.setMail(input.getMail());
            entity.setPassword(encodedPassword);
            entity.setSearchDateTime(now);

            // Sauvegarde dans la base
            userRepositoryJpa.save(entity);

            // Retour DTO
            outputs.add(UserDataAccessDto.PostOutput.builder()
                    .id(entity.getId())
                    .mail(entity.getMail())
                    .password(entity.getPassword())
                    .searchDateTime(entity.getSearchDateTime())
                    .build());
        }

        return outputs;
    }
}
