package com.formationspring.demo.config;

import com.formationspring.demo.entity.UserDataAccesEntity;
import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.services.UserDataAccessService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {

    private final UserRepositoryJpa userRepository;

    public UserConfig(UserRepositoryJpa userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public UserDataAccessService userDataAccesInterface() {
        return new UserDataAccessService(userRepository);
    }

    @Bean
    public CommandLineRunner userSave() {
        return args -> {
            userRepository.save(new UserDataAccesEntity(null, "Prenom 1", "Nom 1"));
        };
    }
}