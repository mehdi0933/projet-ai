package com.formationspring.demo.services;

import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.dto.UserDataAccessDto.PostInput;
import com.formationspring.demo.dto.UserDataAccessDto.PostOutput;
import com.formationspring.demo.entity.UserDataAccesEntity;
import com.formationspring.demo.services.Interface.UserDataAccesInterface;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userDataAccesService")
public class UserDataAccessService implements UserDataAccesInterface {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserDataAccessService(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public List<PostOutput> getAllUsers() {
        List<UserDataAccesEntity> entities = userRepositoryJpa.findAll();
        List<PostOutput> postOutputList = new ArrayList<>();

        for (UserDataAccesEntity entity : entities) {
            PostOutput dto = PostOutput.builder()
                    .id(entity.getId())
                    .firstName(entity.getFirstName())
                    .lastName(entity.getLastName())
                    .build();

            postOutputList.add(dto);
        }

        return postOutputList;
    }

    @Override
    public List<PostOutput> saveAllUsers(List<PostInput> users) {
        List<UserDataAccesEntity> entitiesToSave = new ArrayList<>();

        for (PostInput postInputDto : users) {
            UserDataAccesEntity entity = new UserDataAccesEntity(
                    postInputDto.getId(),
                    postInputDto.getFirstName(),
                    postInputDto.getLastName()
            );
            entitiesToSave.add(entity);
        }

        List<UserDataAccesEntity> savedEntities = userRepositoryJpa.saveAll(entitiesToSave);
        List<PostOutput> postOutputList = new ArrayList<>();

        for (UserDataAccesEntity savedEntity : savedEntities) {
            PostOutput dto = PostOutput.builder()
                    .id(savedEntity.getId())
                    .firstName(savedEntity.getFirstName())
                    .lastName(savedEntity.getLastName())
                    .build();

            postOutputList.add(dto);
        }

        return postOutputList;
    }
}
