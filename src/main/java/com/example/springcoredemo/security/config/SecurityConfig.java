package com.example.springcoredemo.security.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.springcoredemo.security.UserPermission.WRITE;
import static com.example.springcoredemo.security.UserRole.ADMIN;
import static com.example.springcoredemo.security.UserRole.ADMINTRAINEE;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        final String apiTemplate = "/api/**";
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                        .requestMatchers("/register/**", "/index", "/confirm").permitAll()
                        .requestMatchers("/users").hasRole(ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, apiTemplate).hasAuthority(WRITE.getPermission())
                        .requestMatchers(HttpMethod.POST, apiTemplate).hasAuthority(WRITE.getPermission())
                        .requestMatchers(HttpMethod.PUT, apiTemplate).hasAuthority(WRITE.getPermission())
                        .requestMatchers(HttpMethod.GET, apiTemplate).hasAnyRole(ADMIN.name(), ADMINTRAINEE.name())
                ).formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/users", true)
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordConfig() {
        return new BCryptPasswordEncoder(12);

    }
}