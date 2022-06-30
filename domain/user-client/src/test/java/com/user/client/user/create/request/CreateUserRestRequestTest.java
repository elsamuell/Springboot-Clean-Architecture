package com.user.client.user.create.request;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.ConstraintViolationException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CreateUserRestRequestTest {

    @Test
    void givenWrongFormatEmail_whenCreateUserRequest_shouldThrowException(){
        CreateUserRestRequest userRestRequest = new CreateUserRestRequest();
        userRestRequest.setEmail("dummy");
        userRestRequest.setName("dummy");
        userRestRequest.setPassword("password");
        Exception e = assertThrows(ConstraintViolationException.class,userRestRequest::validate);
        assertThat(e.getMessage()).isEqualTo("email: Email format is invalid");
    }

    @Test
    void givenNullName_whenCreateUserRequest_shouldThrowException(){
        CreateUserRestRequest userRestRequest = new CreateUserRestRequest();
        userRestRequest.setEmail("dummy@dummy.com");
        userRestRequest.setName(null);
        userRestRequest.setPassword("password");
        Exception e = assertThrows(ConstraintViolationException.class,userRestRequest::validate);
        assertThat(e.getMessage()).isEqualTo("name: Name cannot be empty");
    }

    @Test
    void givenPasswordLessThanMin_whenCreateUserRequest_shouldThrowException(){
        CreateUserRestRequest userRestRequest = new CreateUserRestRequest();
        userRestRequest.setEmail("dummy@dummy.com");
        userRestRequest.setName("dummy");
        userRestRequest.setPassword("pass");
        Exception e = assertThrows(ConstraintViolationException.class,userRestRequest::validate);
        assertThat(e.getMessage()).isEqualTo("password: Password length min 6 character");
    }
}