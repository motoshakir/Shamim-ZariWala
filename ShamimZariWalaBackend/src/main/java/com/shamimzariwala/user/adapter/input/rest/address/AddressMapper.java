package com.shamimzariwala.user.adapter.input.rest.address;

import com.shamimzariwala.user.adapter.output.persistance.address.AddressEntity;
import com.shamimzariwala.user.application.address.command.CreateAddressCommand;
import com.shamimzariwala.user.application.address.command.UpdateAddressCommand;
import com.shamimzariwala.user.domain.address.Address;

public class AddressMapper {

    public static CreateAddressCommand toCommand(Long userId, CreateAddressRequest request) {
        return new CreateAddressCommand(
                userId,
                request.title(),
                request.addressLine1(),
                request.addressLine2(),
                request.country(),
                request.city(),
                request.postalCode(),
                request.phoneNumber());
    }

    public static UpdateAddressCommand toCommand(Long addressId, UpdateAddressRequest request) {
        return new UpdateAddressCommand(
                addressId,
                request.title(),
                request.addressLine1(),
                request.addressLine2(),
                request.country(),
                request.city(),
                request.postalCode(),
                request.phoneNumber());
    }

    public static Address toDomain(AddressEntity entity) {
        if (entity == null) return null;
        return Address.restore(
                entity.getId(),
                entity.getUserId(),
                entity.getTitle(),
                entity.getAddressLine1(),
                entity.getAddressLine2(),
                entity.getCountry(),
                entity.getCity(),
                entity.getPostalCode(),
                entity.getPhoneNumber(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    public static AddressEntity toEntity(Address address) {
        if (address == null) return null;
        return AddressEntity.builder()
                .id(address.getId())
                .userId(address.getUserId())
                .title(address.getTitle())
                .addressLine1(address.getAddressLine1())
                .addressLine2(address.getAddressLine2())
                .country(address.getCountry())
                .city(address.getCity())
                .postalCode(address.getPostalCode())
                .phoneNumber(address.getPhoneNumber())
                .createdAt(address.getCreatedAt())
                .updatedAt(address.getUpdatedAt())
                .build();
    }

    public static AddressResponse toResponse(Address address) {
        return new AddressResponse(
                address.getId(),
                address.getUserId(),
                address.getTitle(),
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCountry(),
                address.getCity(),
                address.getPostalCode(),
                address.getPhoneNumber(),
                address.getCreatedAt(),
                address.getUpdatedAt());
    }
}
