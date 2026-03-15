package com.shamimzariwala.user.adapter.output.persistance;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.shamimzariwala.user.adapter.input.rest.UserMapper;
import com.shamimzariwala.user.application.port.output.UserRepository;
import com.shamimzariwala.user.domain.model.User;

@Repository
public class UserPersistenceAdapter implements UserRepository {

    private final SpringDataUserRepository repository;

    public UserPersistenceAdapter(SpringDataUserRepository repository) {
        this.repository = repository;
    }

    // @Override
    // public User save(User user) {

    // UserEntity entity = new UserEntity(null, user.getEmail(),
    // user.getPassword());

    // UserEntity saved = repository.save(entity);

    // return new User(saved.getEmail(), saved.getPassword());
    // }

    @Override
    public Optional<User> save(User user) {
        UserEntity entity = UserMapper.toEntity(user); // Mapper handles the ID logic
        UserEntity saved = repository.save(entity);
    
        return Optional.ofNullable(UserMapper.toDomain(saved));
    }

    @Override
    public Optional<User> findById(Long userId) {
        return repository.findById(userId)
                .map(UserMapper::toDomain);
    }
}