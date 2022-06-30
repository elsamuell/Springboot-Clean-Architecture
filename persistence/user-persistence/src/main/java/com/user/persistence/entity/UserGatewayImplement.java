package com.user.persistence.entity;

import com.user.core.entity.UserModel;
import com.user.core.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserGatewayImplement implements UserGateway {

    private final UserRepository userRepository;

    @Override
    public UserModel create(UserModel userModel) {
        UserEntity userEntity = UserEntity.valueOf(userModel);
        UserEntity result = userRepository.save(userEntity);
        return UserModel.builder()
                .id(result.getId())
                .password(result.getPassword())
                .email(result.getEmail())
                .name(result.getName())
                .build();
    }

    @Override
    public Optional<UserModel> findByEmail(String email) {
        UserEntity userEntity = userRepository.findByEmail(email).orElse(null);
        return Optional.ofNullable(userEntity!=null?
                UserModel.builder()
                        .id(userEntity.getId())
                        .name(userEntity.getName())
                        .email(userEntity.getName())
                        .password(userEntity.getPassword())
                        .build():null);
    }
}