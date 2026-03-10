package com.shamimzariwala.user.adapter.input.rest;

import com.shamimzariwala.user.domain.model.UserRole;
import com.shamimzariwala.user.domain.model.UserStatus;

public record UpdateUserRequest(      
    String email,    
    String password, 
    UserRole role,
    UserStatus status
) {
    
}
