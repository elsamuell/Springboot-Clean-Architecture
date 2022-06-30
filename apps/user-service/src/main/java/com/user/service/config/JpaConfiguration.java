package com.user.service.config;


import com.user.core.gateway.UserGateway;
import com.user.persistence.entity.UserGatewayImplement;
import com.user.persistence.entity.UserRepository;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;

@Configuration
@EntityScan(basePackages = "com.user.persistence")
@EnableJpaRepositories(basePackages = "com.user.persistence")
@EnableJpaAuditing
public class JpaConfiguration {

    @Bean
    public UserGateway userGateway(UserRepository userRepository) {
        return new UserGatewayImplement(userRepository);
    }

    /*@Bean
    public AuditorAware<String> auditorProvider() {
        return () -> Optional.of("SYSTEM");
    }
     */
}
