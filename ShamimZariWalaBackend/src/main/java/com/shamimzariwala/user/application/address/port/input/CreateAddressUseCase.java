package com.shamimzariwala.user.application.address.port.input;

import com.shamimzariwala.user.application.address.command.CreateAddressCommand;
import com.shamimzariwala.user.domain.address.Address;

public interface CreateAddressUseCase {
    Address createAddress(CreateAddressCommand command);
}
