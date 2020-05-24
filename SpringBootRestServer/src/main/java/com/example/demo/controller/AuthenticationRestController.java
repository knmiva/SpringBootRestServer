package com.example.demo.controller;

import com.example.demo.dto.AuthenticationRequestDto;
import com.example.demo.model.User;
import com.example.demo.model.UserDTO;
import com.example.demo.secure.jwt.JwtTokenProvider;
import com.example.demo.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "/rest/auth/")
public class AuthenticationRestController {
    private final UserServiceImpl userServiceImpl;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticationRestController(UserServiceImpl userServiceImpl, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userServiceImpl = userServiceImpl;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("login")
//    public ResponseEntity login(@RequestBody User user) {
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        System.out.println(requestDto.getUsername());
        try {
            String username = requestDto.getUsername();
            System.out.println("username: " + username);
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User authUser = userServiceImpl.findUserByUsername(username);
            if (authUser == null) {
                throw new UsernameNotFoundException("User with username: " + username + " is not found");
            }
            System.out.println("user pass: " + authUser.getPassword());
            String token = jwtTokenProvider.createToken(username, authUser.getRoles()
                    .stream().collect(Collectors.toSet()));
            System.out.println("Токен выдан");//
            System.out.println(token);
            Map<Object, Object> response = new HashMap<>();//
            response.put("username", username);//
            response.put("token", token);
            UserDTO userDTO = UserDTO.fromUser(authUser);
            response.put("roles", userDTO.getRole());
            return ResponseEntity.ok(response);
//            DtoForLogin dtoForLogin = new DtoForLogin();
//            dtoForLogin.setToken(token);
//            dtoForLogin.setRoles(DtoForLogin.rolesToString(authUser.getRoles()));
//            return ResponseEntity.ok().body(dtoForLogin);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Your username or password is invalid");
        }

    }
}
