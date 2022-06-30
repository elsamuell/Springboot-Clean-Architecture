package com.user.client.user.create.request;

import com.another.common.validator.ValidationAware;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.user.core.entity.UserModel;
import com.user.core.usecase.create.CreateUserRequest;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CreateUserRestRequest extends ValidationAware<CreateUserRequest> implements CreateUserRequest {

    @Email(message ="Email format is invalid")
    String email;
    @NotBlank(message = "Name cannot be empty")
    String name;
    @Size(message = "Password length min 6 character", min = 6)
    String password;

    @JsonIgnore()
    PasswordEncoder passwordEncoder;
    @Override
    public void validate() {
        super.validate();
    }

    @Override
    public UserModel toEntity() {
      return UserModel.builder()
              .email(email)
              .name(name)
              .password(passwordEncoder.encode(password))
              .build();
    }
}
