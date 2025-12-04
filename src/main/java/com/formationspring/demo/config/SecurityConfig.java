package com.formationspring.demo.config;

import com.formationspring.demo.jwt.JwtFilter;
import com.formationspring.demo.dal.UserRepositoryJpa;
import com.formationspring.demo.entity.UserEntity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserRepositoryJpa userRepositoryJpa;

    public SecurityConfig(JwtFilter jwtFilter,
                          UserRepositoryJpa userRepositoryJpa) {
        this.jwtFilter = jwtFilter;
        this.userRepositoryJpa = userRepositoryJpa;
    }

    // SecurityFilterChain — le cœur de la config
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http

                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // URLs publiques
                        .requestMatchers("/", "/index.html", "/css/**", "/js/**", "/images/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/user/login").permitAll()
                        .requestMatchers("/user/post").permitAll()
                        .requestMatchers("/user/test/permisAll").permitAll()
                        .requestMatchers("/user/home.html").permitAll() // <-- ton HTML
                        //.requestMatchers("/ai/**").permitAll()
                        .requestMatchers("/ai/**").permitAll()
                        //.requestMatchers("/ai/hello/home.html").permitAll()
                        .requestMatchers("/ai/hello").permitAll()
                        .requestMatchers("/ai/hello/**").permitAll()


                        // URLs sécurisées
                        .requestMatchers("/user/test/security").authenticated()
                       // .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
                        //.requestMatchers("/ai/**").hasAnyRole("USER", "ADMIN")


                        //.requestMatchers("/admin/**").hasRole("ADMIN")

                        // Toute autre URL nécessite authentification
                       // .anyRequest().authenticated()
                        .anyRequest().permitAll()

                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    //  Définition du UserDetailsService
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity user = userRepositoryJpa.findByMail(username);
            if (user == null) throw new UsernameNotFoundException("User not found");
            return new org.springframework.security.core.userdetails.User(
                    user.getMail(),
                    user.getPassword(),
                    new ArrayList<>()
            );
        };
    }

    // AuthenticationManager
    @Bean
    public AuthenticationManager authenticationManager(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    // Service en mémoire pour tester des utilisateurs et rôles sans base de données


}