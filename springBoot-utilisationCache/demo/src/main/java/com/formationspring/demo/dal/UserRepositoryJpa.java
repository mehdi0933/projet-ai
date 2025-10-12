package com.formationspring.demo.dal;

import com.formationspring.demo.entity.UserEntity;
import org.example.dto.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository <UserEntity,Long> {
    UserEntity findByMail(String mail);
}
