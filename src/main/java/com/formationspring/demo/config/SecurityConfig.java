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
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.http.HttpMethod;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;
    private final UserRepositoryJpa userRepositoryJpa;
    private final PasswordEncoder passwordEncoder;

    public SecurityConfig(JwtFilter jwtFilter, UserRepositoryJpa userRepositoryJpa, PasswordEncoder passwordEncoder) {
        this.jwtFilter = jwtFilter;
        this.userRepositoryJpa = userRepositoryJpa;
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        // Pages publiques
                        .requestMatchers("/**").permitAll() // temporaire, pour vérifier si c’est le SecurityConfig

                        //.requestMatchers("*/connexion").permitAll()
                        //.requestMatchers("*/inscription").permitAll()
                        //.requestMatchers("*/historique").permitAll()
                        //.requestMatchers("/connexion").permitAll()
                        //.requestMatchers("/inscription").permitAll()
                        //.requestMatchers("/historique").permitAll()
                       // .requestMatchers("/*.html").permitAll()
                        //.requestMatchers("/css/**").permitAll()
                        //.requestMatchers("/js/**").permitAll()
                        //.requestMatchers("/images/**").permitAll()

                        // Endpoints REST publics
                        //.requestMatchers(HttpMethod.POST, "/user/login", "/user/post").permitAll()
                        //.requestMatchers("/user/test/permisAll").permitAll()
                        //.requestMatchers("/ai/hello", "/ai/hello/**").permitAll()
                        //.requestMatchers("/user/test/security").permitAll()
                        //.requestMatchers("/user/**").permitAll()
                        //.requestMatchers("/ai/**").permitAll()
                        //.requestMatchers("/admin/**").permitAll()
                        // Endpoints sécurisés
                        //.requestMatchers("/user/test/security").authenticated()
                        //.requestMatchers("/user/**").hasAnyAuthority("USER", "ADMIN")
                        //.requestMatchers("/ai/**").hasAnyAuthority("USER", "ADMIN")
                        //.requestMatchers("/admin/**").hasAuthority("ADMIN")

                        // Toute autre requête nécessite authentification
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            UserEntity user = userRepositoryJpa.findByMail(username);
            if (user == null) throw new UsernameNotFoundException("User not found");
            return new org.springframework.security.core.userdetails.User(
                    user.getMail(),
                    user.getPassword(),
                    new ArrayList<>() // les rôles Spring Security seront gérés via JwtFilter
            );
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }
}
