package com.shamimzariwala.user.adapter.input.rest.user;

import com.shamimzariwala.auth.domain.AuthUser;
import com.shamimzariwala.user.adapter.output.persistance.user.UserEntity;
import com.shamimzariwala.user.application.user.command.CreateUserCommand;
import com.shamimzariwala.user.application.user.command.UpdateUserCommand;
import com.shamimzariwala.user.domain.user.User;

public class UserMapper {

    public static CreateUserCommand toCommand(CreateUserRequest request) {
        return new CreateUserCommand(
                request.email(),
                request.password(),
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.avatar(),
                request.gender(),
                request.dateOfBirth());
    }

    public static UpdateUserCommand toCommand(Long userId, UpdateUserRequest request) {
        return new UpdateUserCommand(
                userId,
                request.email(),
                request.password(),
                request.firstName(),
                request.lastName(),
                request.phoneNumber(),
                request.avatar(),
                request.gender(),
                request.dateOfBirth(),
                request.role(),
                request.status());
    }

    public static User toDomain(UserEntity userEntity) {
        if (userEntity == null) return null;

        return User.restore(
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getPassword(),
                userEntity.getAvatar(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.getPhoneNumber(),
                userEntity.getDateOfBirth(),
                userEntity.getGender(),
                userEntity.getRole(),
                userEntity.getStatus());
    }

    public static UserEntity toEntity(User user) {
        if (user == null) return null;

        return UserEntity.builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .avatar(user.getAvatar())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .dateOfBirth(user.getDateOfBirth())
                .gender(user.getGender())
                .role(user.getRole())
                .status(user.getStatus())
                .build();
    }

    public static UserResponse toResponse(User user) {
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName());
    }

    public static AuthUser toAuthUserDTO(User user) {
        if (user == null) return null;
        return new AuthUser(user.getEmail(), user.getPassword(), user.getRole());
    }
}
