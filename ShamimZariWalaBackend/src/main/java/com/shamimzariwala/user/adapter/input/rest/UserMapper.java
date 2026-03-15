package com.shamimzariwala.user.adapter.input.rest;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.shamimzariwala.auth.domain.AuthUser;
import com.shamimzariwala.user.adapter.output.persistance.UserEntity;
import com.shamimzariwala.user.application.command.CreateUserCommand;
import com.shamimzariwala.user.application.command.UpdateUserCommand;
import com.shamimzariwala.user.domain.model.User;

public class UserMapper {

    public static CreateUserCommand toCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.email(),
                request.password());
    }

    public static UpdateUserCommand toCommand(Long userId, UpdateUserRequest request) {
        return new UpdateUserCommand(
                userId,
                request.email(),
                request.password(),
                request.role(),
                request.status());
    }

    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null)
            return null;

        User user = new User(
                userEntity.getEmail(),
                userEntity.getPassword());

        user.setId(userEntity.getId());

        return user;
    }

    public static UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getStatus());
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getEmail());
    }

    public static AuthUser toAuthUserDTO(User user) {
        if (user == null) {
            return null;
        }
       
        return new AuthUser(user.getEmail(), user.getPassword(), user.getRole());
    }
}