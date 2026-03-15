package com.shamimzariwala.auth.domain;

public class AuthToken {

    private String accessToken;
    private long expiry;

    public AuthToken(String accessToken, long expiry) {
        this.accessToken = accessToken;
        this.expiry = expiry;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public long getExpiry() {
        return expiry;
    }
}