package com.shamimzariwala.user.adapter.input.rest.user;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shamimzariwala.common.response.PageResponse;
import com.shamimzariwala.user.application.user.command.CreateUserCommand;
import com.shamimzariwala.user.application.user.command.UpdateUserCommand;
import com.shamimzariwala.user.application.user.port.input.CreateUserUseCase;
import com.shamimzariwala.user.application.user.port.input.DeleteUserUseCase;
import com.shamimzariwala.user.application.user.port.input.GetUserQuery;
import com.shamimzariwala.user.application.user.port.input.UpdateUserUseCase;
import com.shamimzariwala.user.domain.user.User;
import com.shamimzariwala.user.domain.user.UserNotFoundException;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations related to B2B and B2C user accounts")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserQuery getUserQuery;
    private final DeleteUserUseCase deleteUserUserCase;

    public UserController(CreateUserUseCase createUserUseCase, GetUserQuery getUserQuery,
            UpdateUserUseCase updateUserUseCase, DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserQuery = getUserQuery;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUserCase = deleteUserUseCase;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest request) {

        CreateUserCommand command = UserMapper.toCommand(request);
        User user = createUserUseCase.createUser(command);
        UserResponse userResponse = UserMapper.toResponse(user);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(location).body(userResponse);
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {

        UpdateUserCommand command = UserMapper.toCommand(id, request);
        User user = updateUserUseCase.update(command);

        return UserMapper.toResponse(user);
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = getUserQuery.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return UserMapper.toResponse(user);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<UserResponse> getUsers(@ParameterObject Pageable pageable) {

        Page<User> users = getUserQuery.findAll(pageable);

        Page<UserResponse> response = users.map(UserMapper::toResponse);

        return new PageResponse<>(response);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        deleteUserUserCase.deleteById(id);
    }
}
