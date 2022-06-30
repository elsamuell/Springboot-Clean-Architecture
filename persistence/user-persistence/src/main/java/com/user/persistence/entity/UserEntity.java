package com.user.persistence.entity;

import com.user.core.entity.UserModel;
import com.user.persistence.common.AuditTrail;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Entity
@Table(name = "user_table")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserEntity extends AuditTrail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String email;
    @Column
    String name;
    @Column
    String password;


    public static UserEntity valueOf(UserModel userModel) {
        UserEntity user = UserEntity.builder()
                .email(userModel.getEmail())
                .name(userModel.getName())
                .password(userModel.getPassword())
                .build();
        user.setCreatedBy(userModel.getName());
        return user;
    }
}
