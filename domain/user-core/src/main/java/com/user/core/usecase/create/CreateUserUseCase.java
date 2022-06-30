package com.user.core.usecase.create;

import com.user.core.entity.UserModel;
import com.user.core.gateway.UserGateway;
import com.user.core.exception.UserException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateUserUseCase implements CreateUser{

    private final UserGateway userGateway;

    @Override
    public void create(CreateUserRequest request, CreateUserPresenter presenter) {
        validateRequest(request);;
        checkUserExist(request.getEmail());
        doCreate(request, presenter);
    }

    private void doCreate(CreateUserRequest request, CreateUserPresenter presenter) {
        UserModel userModel = userGateway.create(request.toEntity());
        presenter.present(userModel);
    }

    private void validateRequest(CreateUserRequest request){
        request.validate();
    }

    private void checkUserExist(String email) {
        userGateway.findByEmail(email).ifPresent(value->{
            throw new UserException("User has been registered");
        });
    }
}
