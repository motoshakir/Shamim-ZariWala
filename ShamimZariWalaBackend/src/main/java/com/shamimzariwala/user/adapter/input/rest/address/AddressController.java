package com.shamimzariwala.user.adapter.input.rest.address;

import java.net.URI;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.shamimzariwala.common.response.PageResponse;
import com.shamimzariwala.user.application.address.command.CreateAddressCommand;
import com.shamimzariwala.user.application.address.command.UpdateAddressCommand;
import com.shamimzariwala.user.application.address.port.input.CreateAddressUseCase;
import com.shamimzariwala.user.application.address.port.input.DeleteAddressUseCase;
import com.shamimzariwala.user.application.address.port.input.GetAddressQuery;
import com.shamimzariwala.user.application.address.port.input.UpdateAddressUseCase;
import com.shamimzariwala.user.domain.address.Address;
import com.shamimzariwala.user.domain.address.AddressNotFoundException;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/users/{userId}/addresses")
@Tag(name = "User Addresses", description = "Mailing/shipping addresses owned by a user (Home, Office, etc.)")
public class AddressController {

    private final CreateAddressUseCase createAddressUseCase;
    private final UpdateAddressUseCase updateAddressUseCase;
    private final GetAddressQuery getAddressQuery;
    private final DeleteAddressUseCase deleteAddressUseCase;

    public AddressController(CreateAddressUseCase createAddressUseCase,
                             UpdateAddressUseCase updateAddressUseCase,
                             GetAddressQuery getAddressQuery,
                             DeleteAddressUseCase deleteAddressUseCase) {
        this.createAddressUseCase = createAddressUseCase;
        this.updateAddressUseCase = updateAddressUseCase;
        this.getAddressQuery = getAddressQuery;
        this.deleteAddressUseCase = deleteAddressUseCase;
    }

    @PostMapping
    public ResponseEntity<AddressResponse> create(@PathVariable Long userId,
                                                  @Valid @RequestBody CreateAddressRequest request) {
        CreateAddressCommand command = AddressMapper.toCommand(userId, request);
        Address address = createAddressUseCase.createAddress(command);
        AddressResponse response = AddressMapper.toResponse(address);
        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{uid}/addresses/{aid}")
                .buildAndExpand(userId, address.getId()).toUri();
        return ResponseEntity.created(location).body(response);
    }

    @PutMapping("/{addressId}")
    public AddressResponse update(@PathVariable Long userId,
                                  @PathVariable Long addressId,
                                  @Valid @RequestBody UpdateAddressRequest request) {
        UpdateAddressCommand command = AddressMapper.toCommand(addressId, request);
        Address address = updateAddressUseCase.update(command);
        return AddressMapper.toResponse(address);
    }

    @GetMapping("/{addressId}")
    public AddressResponse getById(@PathVariable Long userId, @PathVariable Long addressId) {
        Address address = getAddressQuery.findById(addressId)
                .orElseThrow(() -> new AddressNotFoundException(addressId));
        return AddressMapper.toResponse(address);
    }

    @GetMapping(produces = "application/json")
    public PageResponse<AddressResponse> list(@PathVariable Long userId,
                                              @ParameterObject Pageable pageable) {
        Page<Address> addresses = getAddressQuery.findAllByUserId(userId, pageable);
        Page<AddressResponse> response = addresses.map(AddressMapper::toResponse);
        return new PageResponse<>(response);
    }

    @DeleteMapping("/{addressId}")
    public void delete(@PathVariable Long userId, @PathVariable Long addressId) {
        deleteAddressUseCase.deleteById(addressId);
    }
}
