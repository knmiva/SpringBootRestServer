package com.example.demo.dto;

import com.example.demo.model.Role;
import com.example.demo.model.User;

import java.util.Set;

public class DtoForClient {
    private Long id;
    private String username;
    private String password;
    private Set<Role> roles;

    public DtoForClient() {
    }

    public DtoForClient(Long id, String username, String password, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public DtoForClient(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public static DtoForClient getDtoForClient (User user){
        DtoForClient dtoForClient = new DtoForClient();
        dtoForClient.setId(user.getId());
        dtoForClient.setUsername(user.getUsername());
        dtoForClient.setPassword(user.getPassword());
        dtoForClient.setRoles(user.getRoles());
        return dtoForClient;
    }

    public static User getUserFromDto(DtoForClient userForClient) {
        User user = new User();
        user.setUsername(userForClient.getUsername());
        user.setPassword(userForClient.getPassword());
        user.setRoles(userForClient.getRoles());
        return user;
    }
}
