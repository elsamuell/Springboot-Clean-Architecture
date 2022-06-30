package com.user.service.user.create;

import com.user.client.user.create.request.CreateUserRestRequest;
import com.user.client.user.create.response.CreateUserRestResponse;
import com.user.core.usecase.create.CreateUser;
import com.user.service.constant.Routes;
import io.swagger.v3.oas.annotations.OpenAPI31;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Create User")
@RestController
@RequiredArgsConstructor
public class CreateUserController {

    private final CreateUser createUser;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(Routes.USER)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateUserRestResponse create(@RequestBody CreateUserRestRequest request) {
        request.setPasswordEncoder(passwordEncoder);
        CreateUserRestPresenter restPresenter = new CreateUserRestPresenter();
        createUser.create(request, restPresenter);
        return restPresenter.getResponse();
    }


}
