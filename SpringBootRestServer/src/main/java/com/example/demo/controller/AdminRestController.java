package com.example.demo.controller;

import com.example.demo.dto.DtoForClient;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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
            return ResponseEntity.notFound().build();
        }
        List<DtoForClient> dtoUsersForClient = new ArrayList<>();
        for (User user : users) {
            dtoUsersForClient.add(DtoForClient.getDtoForClient(user));
        }
        return ResponseEntity.ok().body(dtoUsersForClient);
    }

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
        return ResponseEntity.ok().body(dtoUser);
    }

    @GetMapping("/editUser/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(value = "id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/editUser")
    public void updateUser(@RequestBody User editUser) {
        userServiceImpl.updateUser(editUser);
    }

//    @GetMapping("/editUser/{id}")
//    public ResponseEntity<DtoForClient> getUserById(@PathVariable(value = "id") Long id) {
//        User user = userServiceImpl.getUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        DtoForClient dtoUserForClient = DtoForClient.getDtoForClient(user);
//        return ResponseEntity.ok().body(dtoUserForClient);
//    }
//
//    @PutMapping("/editUser/{id}")
//    public ResponseEntity<DtoForClient> updateUser(@PathVariable(value = "id") Long id, @Valid @RequestBody DtoForClient editUser) {
//        User user = userServiceImpl.getUserById(id);
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        user.setUsername(editUser.getUsername());
//        user.setPassword(editUser.getPassword());
//        user.setRoles(editUser.getRoles());
//        userServiceImpl.updateUser(user);
//        return ResponseEntity.ok().body(DtoForClient.getDtoForClient(user));
//    }

    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<DtoForClient> deleteUser(@PathVariable(value = "id") Long id) {
        User userFromDB = userServiceImpl.getUserById(id);
        if (userFromDB == null) {
            return ResponseEntity.notFound().build();
        }
        userServiceImpl.deleteUser(userFromDB.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<DtoForClient> viewAdmin(@PathVariable(name = "id") Long id) {
        User user = userServiceImpl.getUserById(id);
        if (user == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(DtoForClient.getDtoForClient(user));
    }
}




