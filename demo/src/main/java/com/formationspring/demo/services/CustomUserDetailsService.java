package com.formationspring.demo.security;

import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.entity.UserEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepositoryJpa userRepositoryJpa;

    public CustomUserDetailsService(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
        UserEntity user = userRepositoryJpa.findByMail(mail);

        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + mail);
        }

        return new User(user.getMail(), user.getPassword(), Collections.emptyList());
    }
}
