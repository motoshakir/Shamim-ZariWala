package com.shamimzariwala.user.domain.address;

import java.time.LocalDateTime;
import java.util.Objects;

public class Address {

    private Long id;
    private Long userId;
    private String title;
    private String addressLine1;
    private String addressLine2;
    private String country;
    private String city;
    private String postalCode;
    private String phoneNumber;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected Address() {}

    private Address(Long userId,
                    String title,
                    String addressLine1,
                    String country,
                    String city,
                    String postalCode,
                    String phoneNumber) {
        this.userId = Objects.requireNonNull(userId, "User id is required");
        this.title = requireNonBlank(title, "Title is required");
        this.addressLine1 = requireNonBlank(addressLine1, "Address line 1 is required");
        this.country = requireNonBlank(country, "Country is required");
        this.city = requireNonBlank(city, "City is required");
        this.postalCode = requireNonBlank(postalCode, "Postal code is required");
        this.phoneNumber = requireNonBlank(phoneNumber, "Phone number is required");
    }

    public static Address create(Long userId,
                                 String title,
                                 String addressLine1,
                                 String addressLine2,
                                 String country,
                                 String city,
                                 String postalCode,
                                 String phoneNumber) {
        Address address = new Address(userId, title, addressLine1, country, city, postalCode, phoneNumber);
        address.addressLine2 = addressLine2;
        return address;
    }

    public static Address restore(
            Long id,
            Long userId,
            String title,
            String addressLine1,
            String addressLine2,
            String country,
            String city,
            String postalCode,
            String phoneNumber,
            LocalDateTime createdAt,
            LocalDateTime updatedAt) {

        Address address = new Address();
        address.id = id;
        address.userId = userId;
        address.title = title;
        address.addressLine1 = addressLine1;
        address.addressLine2 = addressLine2;
        address.country = country;
        address.city = city;
        address.postalCode = postalCode;
        address.phoneNumber = phoneNumber;
        address.createdAt = createdAt;
        address.updatedAt = updatedAt;
        return address;
    }

    public void update(String title,
                       String addressLine1,
                       String addressLine2,
                       String country,
                       String city,
                       String postalCode,
                       String phoneNumber) {
        if (title != null && !title.isBlank()) this.title = title;
        if (addressLine1 != null && !addressLine1.isBlank()) this.addressLine1 = addressLine1;
        if (addressLine2 != null) this.addressLine2 = addressLine2;
        if (country != null && !country.isBlank()) this.country = country;
        if (city != null && !city.isBlank()) this.city = city;
        if (postalCode != null && !postalCode.isBlank()) this.postalCode = postalCode;
        if (phoneNumber != null && !phoneNumber.isBlank()) this.phoneNumber = phoneNumber;
    }

    private static String requireNonBlank(String s, String message) {
        if (s == null || s.isBlank()) throw new IllegalArgumentException(message);
        return s;
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getTitle() { return title; }
    public String getAddressLine1() { return addressLine1; }
    public String getAddressLine2() { return addressLine2; }
    public String getCountry() { return country; }
    public String getCity() { return city; }
    public String getPostalCode() { return postalCode; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
