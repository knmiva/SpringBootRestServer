package com.example.demo.dto;

import com.example.demo.model.Role;

import java.util.Set;

public class DtoForLogin {
    private String login;
    private String token;
    private String roles;

    public DtoForLogin(String login, String token, String roles) {
        this.login = login;
        this.token = token;
        this.roles = roles;
    }

    public DtoForLogin() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public static String rolesToString (Set<Role> userRoles) {
        StringBuilder dtoRoles = new StringBuilder();
        for (Role role:userRoles) {
            dtoRoles.append(role.getRole());
        }
        return dtoRoles.toString();
    }
}
