package com.user.service.user.create;

import com.user.client.user.create.response.CreateUserRestResponse;
import com.user.core.entity.UserModel;
import com.user.core.usecase.create.CreateUserPresenter;

public class CreateUserRestPresenter implements CreateUserPresenter {

    private CreateUserRestResponse response;

    @Override
    public void present(UserModel userModel) {
        response = new CreateUserRestResponse(userModel.getId());
    }

    public CreateUserRestResponse getResponse() {
        return response;
    }
}
