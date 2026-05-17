package com.shamimzariwala.user.domain.user;

import java.time.LocalDate;
import java.util.Objects;

public class User {

    private Long id;
    private String email;
    private String password;
    private String avatar;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private String gender;
    private UserRole role;
    private UserStatus status;

    protected User() {}

    private User(String email, String password, UserRole role, UserStatus status) {
        this.email = Objects.requireNonNull(email, "Email is required");
        this.password = Objects.requireNonNull(password, "Password is required");
        this.role = Objects.requireNonNull(role, "Role is required");
        this.status = Objects.requireNonNull(status, "Status is required");
    }

    public static User create(String email, String password) {
        return new User(email, password, UserRole.B2C_CUSTOMER, UserStatus.ACTIVE);
    }

    public static User createAdmin(String email, String password) {
        return new User(email, password, UserRole.SYSTEM_ADMIN, UserStatus.ACTIVE);
    }

    public static User restore(
            Long id,
            String email,
            String password,
            String avatar,
            String firstName,
            String lastName,
            String phoneNumber,
            LocalDate dateOfBirth,
            String gender,
            UserRole role,
            UserStatus status) {

        User user = new User();
        user.id = id;
        user.email = email;
        user.password = password;
        user.avatar = avatar;
        user.firstName = firstName;
        user.lastName = lastName;
        user.phoneNumber = phoneNumber;
        user.dateOfBirth = dateOfBirth;
        user.gender = gender;
        user.role = role;
        user.status = status;

        return user;
    }

    public void updateProfile(
            String firstName,
            String lastName,
            String phoneNumber,
            String avatar,
            String gender,
            LocalDate dob) {

        if (firstName != null) this.firstName = firstName;
        if (lastName != null) this.lastName = lastName;
        if (phoneNumber != null) this.phoneNumber = phoneNumber;
        if (avatar != null) this.avatar = avatar;
        if (gender != null) this.gender = gender;

        if (dob != null) setBirthday(dob);
    }

    public void setBirthday(LocalDate dob) {
        if (dob != null && dob.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }
        this.dateOfBirth = dob;
    }

    public void changeRole(UserRole newRole) {
        this.role = Objects.requireNonNull(newRole, "Role is required");
    }

    public void deactivate() {
        this.status = UserStatus.DEACTIVATED;
    }

    public void activate() {
        this.status = UserStatus.ACTIVE;
    }

    public void updateEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail, "New email cannot be null");
    }

    public void changePassword(String newHashedPassword) {
        this.password = Objects.requireNonNull(newHashedPassword, "Password cannot be null");
    }

    public void changeStatus(UserStatus status) {
        this.status = Objects.requireNonNull(status, "Status cannot be null");
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getAvatar() { return avatar; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhoneNumber() { return phoneNumber; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public String getGender() { return gender; }
    public UserRole getRole() { return role; }
    public UserStatus getStatus() { return status; }

    public String getFullName() {
        if (firstName == null && lastName == null) return "Guest";
        return (firstName + " " + (lastName != null ? lastName : "")).trim();
    }

    public String getAvatarUrl() {
        return (avatar == null || avatar.isEmpty())
                ? "/images/default-avatar.png"
                : avatar;
    }
}
