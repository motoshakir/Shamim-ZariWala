package com.shamimzariwala.user.domain.address;

public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(Long id) {
        super("Address with ID " + id + " was not found.");
    }
}
