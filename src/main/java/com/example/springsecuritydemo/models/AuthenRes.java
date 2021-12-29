package com.example.springsecuritydemo.models;

public class AuthenRes {
    private final String jwt;

    public AuthenRes(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }
}
