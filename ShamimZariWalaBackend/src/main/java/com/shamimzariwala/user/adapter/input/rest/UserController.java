package com.shamimzariwala.user.adapter.input.rest;

import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.shamimzariwala.user.application.port.input.DeleteUserUseCase;
import com.shamimzariwala.user.application.port.input.GetUserQuery;
import com.shamimzariwala.user.application.port.input.UpdateUserUseCase;
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
    private final DeleteUserUseCase deleteUserUserCase;    

    public UserController(CreateUserUseCase createUserUseCase, GetUserQuery getUserQuery,
            UpdateUserUseCase updateUserUseCase,DeleteUserUseCase deleteUserUseCase) {
        this.createUserUseCase = createUserUseCase;
        this.getUserQuery = getUserQuery;
        this.updateUserUseCase = updateUserUseCase;
        this.deleteUserUserCase = deleteUserUseCase;
    }

    @PostMapping("/create")
    public UserResponse createUser(@RequestBody CreateUserRequest request) {

        CreateUserCommand command = UserMapper.toCommand(request);

        User user = createUserUseCase.createUser(command);

        return new UserResponse(user.getEmail());
    }

    @PutMapping("/update/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request) {

        UpdateUserCommand command = UserMapper.toCommand(id, request);

        User user = updateUserUseCase.update(command);

        return new UserResponse(user.getEmail());
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id) {
        User user = getUserQuery.findUserById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return new UserResponse(user.getEmail());
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
      
       deleteUserUserCase.deleteUser(id);

    }
}
