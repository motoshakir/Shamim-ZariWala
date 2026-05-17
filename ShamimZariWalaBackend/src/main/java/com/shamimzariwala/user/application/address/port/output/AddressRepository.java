package com.shamimzariwala.user.application.address.port.output;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shamimzariwala.user.domain.address.Address;

public interface AddressRepository {
    Address save(Address address);
    Optional<Address> findById(Long addressId);
    Page<Address> findAllByUserId(Long userId, Pageable pageable);
    void deleteById(Long addressId);
}
