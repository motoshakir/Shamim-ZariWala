package com.shamimzariwala.user.application.port.input;

import java.util.Optional;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.user.domain.model.User;

public interface GetUserQuery {
       Optional<User> findById(Long userId);
       Page<User> findAll(Pageable pageable);

}