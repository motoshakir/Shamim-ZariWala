package com.shamimzariwala.user.adapter.output.persistance.address;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.shamimzariwala.user.adapter.input.rest.address.AddressMapper;
import com.shamimzariwala.user.application.address.port.output.AddressRepository;
import com.shamimzariwala.user.domain.address.Address;
import com.shamimzariwala.user.domain.address.AddressNotFoundException;

@Repository
public class AddressPersistenceAdapter implements AddressRepository {

    private final SpringDataAddressRepository repository;

    public AddressPersistenceAdapter(SpringDataAddressRepository repository) {
        this.repository = repository;
    }

    @Override
    public Address save(Address address) {
        AddressEntity entity = AddressMapper.toEntity(address);
        AddressEntity saved = repository.save(entity);
        return AddressMapper.toDomain(saved);
    }

    @Override
    public Optional<Address> findById(Long addressId) {
        return repository.findById(addressId).map(AddressMapper::toDomain);
    }

    @Override
    public Page<Address> findAllByUserId(Long userId, Pageable pageable) {
        return repository.findAllByUserId(userId, pageable).map(AddressMapper::toDomain);
    }

    @Override
    public void deleteById(Long addressId) {
        if (!repository.findById(addressId).isPresent()) {
            throw new AddressNotFoundException(addressId);
        }
        repository.deleteById(addressId);
    }
}
