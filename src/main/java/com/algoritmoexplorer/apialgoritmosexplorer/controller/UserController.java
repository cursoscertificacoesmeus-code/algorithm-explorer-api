package com.algoritmoexplorer.apialgoritmosexplorer.controller;

import com.algoritmoexplorer.apialgoritmosexplorer.modal.User;
import com.algoritmoexplorer.apialgoritmosexplorer.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User newUser) {
        User createdUser = userService.createUser(newUser);

        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
