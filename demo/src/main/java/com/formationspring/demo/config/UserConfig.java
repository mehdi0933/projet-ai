package com.formationspring.demo.config;

import com.formationspring.demo.entity.UserDataAccesEntity;
import com.formationspring.demo.dal.UserRepositoryJpa;
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
    public CommandLineRunner userSave() {
        return args -> {
            // Création d'un utilisateur avec Lombok Builder
            UserDataAccesEntity user = UserDataAccesEntity.builder()
                    .firstName("Prenom 1")
                    .lastName("Nom 1")
                    .build();

            userRepository.save(user);
        };
    }
}
