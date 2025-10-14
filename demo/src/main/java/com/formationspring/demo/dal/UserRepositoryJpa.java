package com.formationspring.demo.dal;

import com.formationspring.demo.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepositoryJpa extends JpaRepository <UserEntity,Long> {
    UserEntity findByMail(String mail);
    List<UserEntity> findAllByMailIn(List<String> mails);

}
