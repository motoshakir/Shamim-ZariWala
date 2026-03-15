package com.shamimzariwala.auth.adapter.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.shamimzariwala.auth.application.port.output.LoadUser;
import com.shamimzariwala.auth.domain.AuthUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    LoadUser loadUser;

    public UserDetailsServiceImpl(LoadUser loadUser) {
        this.loadUser = loadUser;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthUser user = loadUser.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return org.springframework.security.core.userdetails.User.builder()
            .username(user.email())
            .password(user.password())
            .authorities(user.roles().name()) 
            .build();
    }
    
}
