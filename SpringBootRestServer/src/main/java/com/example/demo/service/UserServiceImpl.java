package com.example.demo.service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.getRoleById(id);
    }

    //взять юзера по id
    public User getUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        } else {
            throw new RuntimeException();
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public void addUser(User addUser) {
        addUser.setPassword(passwordEncoder.encode(addUser.getPassword()));
        //проверка имени
        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(addUser.getUsername())) {
                addUser.setUsername(user.getUsername() + "_copy");
            }
        }
//        userRepository.save(addUser);
        userRepository.save(addUser);
    }

    public void updateUser(User editUser) {
        //проверка пароля
        User oldUser = getUserById(editUser.getId());
        String oldPassword = oldUser.getPassword();
        String newPassword = editUser.getPassword();

        if (newPassword.length() > 2 && newPassword.length() < 6) {
            editUser.setPassword(passwordEncoder.encode(newPassword));
        } else {
            editUser.setPassword(oldPassword);
        }

        List<User> users = getAllUsers();
        for (User user : users) {
            if (user.getUsername().equals(editUser.getUsername())) {
                editUser.setUsername(oldUser.getUsername());
            }
        }
        userRepository.save(editUser);
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }
}
