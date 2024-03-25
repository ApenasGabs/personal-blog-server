package com.apenasgabs.blog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apenasgabs.blog.model.UserLogin;
import com.apenasgabs.blog.model.User;
import com.apenasgabs.blog.repository.UserRepository;
import com.apenasgabs.blog.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "User Controller", description = "Controller for user operations")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/all")
    @Operation(summary = "Get all users", responses = {
        @ApiResponse(description = "Successful Operation", responseCode = "200", content = @Content),
        @ApiResponse(description = "No users found", responseCode = "404")
    })
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a user by ID", responses = {
        @ApiResponse(description = "User found", responseCode = "200", content = @Content),
        @ApiResponse(description = "User not found", responseCode = "404")
    })
    public ResponseEntity<User> getById(@PathVariable Long id) {
        return userRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate a user", responses = {
        @ApiResponse(description = "Authentication successful", responseCode = "200", content = @Content),
        @ApiResponse(description = "Authentication failed", responseCode = "401")
    })
    public ResponseEntity<UserLogin> authenticateUser(@RequestBody Optional<UserLogin> userLogin){
        return userService.authenticateUser(userLogin)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", responses = {
        @ApiResponse(description = "User created", responseCode = "201", content = @Content),
        @ApiResponse(description = "Bad request", responseCode = "400")
    })
    public ResponseEntity<User> postUser(@RequestBody @Valid User user) {
        return userService.registerUser(user)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
    }

    @PutMapping("/update")
    @Operation(summary = "Update an existing user", responses = {
        @ApiResponse(description = "User updated", responseCode = "200", content = @Content),
        @ApiResponse(description = "User not found", responseCode = "404")
    })
    public ResponseEntity<User> updateUser(@Valid @RequestBody User user) {
        return userService.updateUser(user)
                .map(response -> ResponseEntity.status(HttpStatus.OK).body(response))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
