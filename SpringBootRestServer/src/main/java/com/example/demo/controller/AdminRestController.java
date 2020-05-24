package com.example.demo.controller;

import com.example.demo.dto.DtoForClient;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rest/admin")
public class AdminRestController {

    private UserServiceImpl userServiceImpl;

    @Autowired
    public AdminRestController(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/users")
    public ResponseEntity<List<DtoForClient>> getAllUsers() {
        List<User> users = userServiceImpl.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<DtoForClient> dtoUsersForClient = new ArrayList<>();
        for (User user : users) {
            dtoUsersForClient.add(DtoForClient.getDtoForClient(user));
        }

        return ResponseEntity.ok().body(dtoUsersForClient);
    }

    /*@GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        System.out.println("Request for list of all users");
        List<User> users = this.userServiceImpl.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<UserDTO> usersDto = new ArrayList<>();
        for (User user : users) {
            usersDto.add(UserDTO.fromUser(user));
        }

        return new ResponseEntity<>(usersDto, HttpStatus.OK);
    }*/

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userServiceImpl.getAllRoles());
    }

    @GetMapping("/roles/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable(value = "id") Long roleId) {
        Role role = userServiceImpl.getRoleById(roleId);
        if (role == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(role);
    }

    @PostMapping("/addUser")
    public ResponseEntity<DtoForClient> addUser(@RequestBody DtoForClient dtoUser) {
        User userFromDB = userServiceImpl.findUserByUsername(dtoUser.getUsername());
        if (userFromDB != null) {
            return ResponseEntity.notFound().build();
        }
        User user = DtoForClient.getUserFromDto(dtoUser);
        userServiceImpl.addUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/editUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    /*public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "id") Long userId) {
//        User user = userServiceImpl.getUserById(id);
        if (userId == null) {
            System.out.println("User's ID does not get");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user = this.userServiceImpl.getUserById(userId);
        if (user == null) {
            System.out.println("User with this ID is not founded");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(UserDTO.fromUser(user), HttpStatus.OK);

        *//*DtoForClient dtoForClient = DtoForClient.getDtoForClient(user);
        return ResponseEntity.ok().body(dtoForClient);*//*
    }*/

    @PutMapping("/editUser")
    public void updateUser(@RequestBody User editUser) {
        userServiceImpl.updateUser(editUser);
    }
    /*public void updateUser(@RequestBody UserDTO editUser) {
        System.out.println();
        userServiceImpl.updateUser(editUser);
    }*/
    /*public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO) {
//        UserDTO userDTO = userServiceImpl.getUserById(userId);
        if (userDTO == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        System.out.println("Collected a new user from page");
        System.out.println(userDTO);
        this.userServiceImpl.updateUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
        *//*userDTO.setUsername(editUser.getUsername());
        userDTO.setPassword(editUser.getPassword());
        userDTO.setRoles(editUser.getRoles());
        userServiceImpl.updateUser(userDTO);
        return ResponseEntity.ok().body(DtoForClient.getDtoForClient(userDTO));*//*
    }*/

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<DtoForClient> deleteUser(@PathVariable(value = "id") Long id) {
        User userFromDB = userServiceImpl.getUserById(id);
        if (userFromDB == null) {
            return ResponseEntity.notFound().build();
        }
        userServiceImpl.deleteUser(userFromDB.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DtoForClient> viewAdmin(@PathVariable(name = "id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(DtoForClient.getDtoForClient(user), HttpStatus.OK);
    }
}




