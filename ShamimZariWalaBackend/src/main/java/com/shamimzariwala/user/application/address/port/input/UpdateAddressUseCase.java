package com.shamimzariwala.user.application.address.port.input;

import com.shamimzariwala.user.application.address.command.UpdateAddressCommand;
import com.shamimzariwala.user.domain.address.Address;

public interface UpdateAddressUseCase {
    Address update(UpdateAddressCommand command);
}
