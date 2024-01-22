package com.example.springcoredemo.security.config;


import com.example.springcoredemo.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.example.springcoredemo.security.UserPermission.WRITE;
import static com.example.springcoredemo.security.UserRole.ADMIN;
import static com.example.springcoredemo.security.UserRole.MANAGER;
import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
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
                        .requestMatchers(HttpMethod.GET, apiTemplate).hasAnyRole(ADMIN.name(), MANAGER.name())
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
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin
                        )
                )
                .httpBasic(withDefaults());
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordConfig() {
        return new BCryptPasswordEncoder(12);

    }
        @Bean
        public DaoAuthenticationProvider daoAuthenticationProvider() {
            DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
            provider.setPasswordEncoder(passwordConfig());
            provider.setUserDetailsService(customUserDetailsService);
            return provider;
        }

        @Bean
        public AuthenticationManager authenticationManager() {
            return new ProviderManager(daoAuthenticationProvider());
        }
}
