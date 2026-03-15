package com.shamimzariwala.user.adapter.input.rest;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shamimzariwala.user.application.command.CreateUserCommand;
import com.shamimzariwala.user.application.command.UpdateUserCommand;
import com.shamimzariwala.user.application.port.input.CreateUserUseCase;
import com.shamimzariwala.user.application.port.input.GetUserQuery;
import com.shamimzariwala.user.application.port.input.UpdateUserUseCase;
import com.shamimzariwala.user.domain.exception.UserAlreadyExistsException;
import com.shamimzariwala.user.domain.exception.UserNotFoundException;
import com.shamimzariwala.user.domain.model.User;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations related to B2B and B2C user accounts")
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final GetUserQuery getUserQuery;

    public UserController(CreateUserUseCase createUserUseCase, GetUserQuery getUserQuery,
            UpdateUserUseCase updateUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserQuery = getUserQuery;
        this.updateUserUseCase = updateUserUseCase;
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {

        CreateUserCommand command = UserMapper.toCommand(request);

        User user = createUserUseCase.createUser(command)
                    .orElseThrow(() -> new UserAlreadyExistsException(command.email()));

        return new UserResponse(user.getEmail());
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {

        UpdateUserCommand command = UserMapper.toCommand(id, request);

        User user = updateUserUseCase.update(command)
                    .orElseThrow(() -> new UserAlreadyExistsException(command.email()));

        return new UserResponse(user.getEmail());
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = getUserQuery.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserResponse(user.getEmail());
    }
}
