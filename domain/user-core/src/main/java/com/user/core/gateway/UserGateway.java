package com.user.core.gateway;

import com.user.core.entity.UserModel;

import java.util.Optional;

public interface UserGateway {
    UserModel create(UserModel userModel);
    Optional<UserModel> findByEmail(String email);
}
