package com.shamimzariwala.user.application.command;

import com.shamimzariwala.user.domain.model.UserRole;
import com.shamimzariwala.user.domain.model.UserStatus;

public record UpdateUserCommand(
    Long userId,     
    String email,    
    String password, 
    UserRole role,
    UserStatus status
) {}
