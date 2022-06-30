package com.user.core.usecase.create;

import com.user.core.common.EntityAwareWithoutParam;
import com.user.core.entity.UserModel;

public interface CreateUserRequest extends EntityAwareWithoutParam<UserModel> {
    String getEmail();
    void validate();
}
