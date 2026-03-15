package com.shamimzariwala.auth.adapter.input;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shamimzariwala.auth.application.port.input.AuthUseCase;
import com.shamimzariwala.auth.domain.AuthToken;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthController {

    private final AuthUseCase authUseCase;

    public AuthController(AuthUseCase authUseCase) {
        this.authUseCase = authUseCase;
    }

    @PostMapping("/login")
    public AuthToken login(@RequestBody LoginRequest request) {
        return authUseCase.login(request.email(), request.password());
    }
}
