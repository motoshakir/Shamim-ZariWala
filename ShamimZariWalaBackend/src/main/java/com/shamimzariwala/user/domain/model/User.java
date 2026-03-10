package com.shamimzariwala.user.domain.model;

import java.util.Objects;

public class User {
    private Long id;
    private String email;
    private String password;
    private UserRole role;
    private UserStatus status;

    public User(String email, String password) {
        this(email, password, UserRole.B2C_CUSTOMER);
    }


    public User(String email, String password, UserRole role) {
        this.email = Objects.requireNonNull(email, "Email is required");
        this.password = Objects.requireNonNull(password, "Password is required");
        this.role = role;
        this.status = UserStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = UserStatus.DEACTIVATED;
    }

    public void active() {
        this.status = UserStatus.ACTIVE;
    }
  

    public void updateEmail(String newEmail) {
        this.email = Objects.requireNonNull(newEmail, "New email cannot be null");
    }


    public void changePassword(String newHashedPassword) {
        this.password = Objects.requireNonNull(newHashedPassword, "Password cannot be null");
    }


    public void changeRole(UserRole newRole) {
        this.role = Objects.requireNonNull(newRole, "Role is required");
    }

    public void changeStatus(UserStatus newStatus) {
        this.status = Objects.requireNonNull(newStatus, "Status is required");
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public UserRole getRole() { return role; }
    public UserStatus getStatus() { return status; }
}
