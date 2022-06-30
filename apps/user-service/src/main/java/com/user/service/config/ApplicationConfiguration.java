package com.user.service.config;

import com.user.core.gateway.UserGateway;
import com.user.core.usecase.create.CreateUser;
import com.user.core.usecase.create.CreateUserUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CreateUser createUser(UserGateway userGateway) {
        return new CreateUserUseCase(userGateway);
    }
}