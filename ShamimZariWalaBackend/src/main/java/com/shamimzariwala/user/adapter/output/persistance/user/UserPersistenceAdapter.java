package com.shamimzariwala.user.adapter.output.persistance.user;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.user.adapter.input.rest.user.UserMapper;
import com.shamimzariwala.user.application.user.port.output.UserRepository;
import com.shamimzariwala.user.domain.user.User;
import com.shamimzariwala.user.domain.user.UserAlreadyExistsException;
import com.shamimzariwala.user.domain.user.UserNotFoundException;

@Repository
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User save(User user) {

        repository.findByEmail(user.getEmail())
                .ifPresent(existingUser -> {
                    if (!existingUser.getId().equals(user.getId())) {
                        throw new UserAlreadyExistsException("Email already in use.");
                    }
                });

        UserEntity entity = UserMapper.toEntity(user);
        UserEntity saved = repository.save(entity);

        return UserMapper.toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId)
                .map(UserMapper::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email)
                .map(UserMapper::toDomain);
    }

    @Override
    public void deleteById(Long userId) {
        if (!repository.findById(userId).isPresent()) {
            throw new UserNotFoundException(userId);
        }
        repository.deleteById(userId);
    }

    @Override
    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(UserMapper::toDomain);
    }
}
