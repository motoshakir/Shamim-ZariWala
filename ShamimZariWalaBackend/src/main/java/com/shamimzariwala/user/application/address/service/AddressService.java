package com.shamimzariwala.user.application.address.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import com.shamimzariwala.user.application.address.command.CreateAddressCommand;
import com.shamimzariwala.user.application.address.command.UpdateAddressCommand;
import com.shamimzariwala.user.application.address.port.input.CreateAddressUseCase;
import com.shamimzariwala.user.application.address.port.input.DeleteAddressUseCase;
import com.shamimzariwala.user.application.address.port.input.GetAddressQuery;
import com.shamimzariwala.user.application.address.port.input.UpdateAddressUseCase;
import com.shamimzariwala.user.application.address.port.output.AddressRepository;
import com.shamimzariwala.user.application.user.port.output.UserRepository;
import com.shamimzariwala.user.domain.address.Address;
import com.shamimzariwala.user.domain.address.AddressNotFoundException;
import com.shamimzariwala.user.domain.user.UserNotFoundException;

public class AddressService implements
        CreateAddressUseCase, UpdateAddressUseCase, GetAddressQuery, DeleteAddressUseCase {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Address createAddress(CreateAddressCommand command) {
        userRepository.findById(command.userId())
                .orElseThrow(() -> new UserNotFoundException(command.userId()));

        Address address = Address.create(
                command.userId(),
                command.title(),
                command.addressLine1(),
                command.addressLine2(),
                command.country(),
                command.city(),
                command.postalCode(),
                command.phoneNumber());

        return addressRepository.save(address);
    }

    @Override
    @Transactional
    public Address update(UpdateAddressCommand command) {
        Address address = addressRepository.findById(command.addressId())
                .orElseThrow(() -> new AddressNotFoundException(command.addressId()));

        address.update(
                command.title(),
                command.addressLine1(),
                command.addressLine2(),
                command.country(),
                command.city(),
                command.postalCode(),
                command.phoneNumber());

        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> findById(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public Page<Address> findAllByUserId(Long userId, Pageable pageable) {
        userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return addressRepository.findAllByUserId(userId, pageable);
    }

    @Override
    public void deleteById(Long addressId) {
        addressRepository.deleteById(addressId);
    }
}
