package com.shamimzariwala.auth.application.port.input;

import com.shamimzariwala.auth.domain.AuthToken;

public interface AuthUseCase {

    AuthToken login(String username, String password);

}