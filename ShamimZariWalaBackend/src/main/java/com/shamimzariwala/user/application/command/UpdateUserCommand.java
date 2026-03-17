package com.shamimzariwala.user.application.command;

import java.time.LocalDate;

import com.shamimzariwala.user.domain.model.UserRole;
import com.shamimzariwala.user.domain.model.UserStatus;

public record UpdateUserCommand(
        Long userId,         
        String email,        
        String password,     
        String firstName,    
        String lastName,     
        String phoneNumber,  
        String avatar,       
        String gender,       
        LocalDate dateOfBirth, 
        UserRole role,       
        UserStatus status    
    ) {}