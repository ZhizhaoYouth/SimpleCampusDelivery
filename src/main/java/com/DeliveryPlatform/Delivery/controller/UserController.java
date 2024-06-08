package com.DeliveryPlatform.Delivery.controller;

import com.DeliveryPlatform.Delivery.entity.User;
import com.DeliveryPlatform.Delivery.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/test")
    @Operation(summary = "Test endpoint", description = "Endpoint to verify the connectivity")
    public String test() {
        return "Test successful";
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user", description = "Register a new user with the provided user details")
    public ResponseEntity<String> registerUser(
            @RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    @Operation(summary = "Login user", description = "Login user with the provided credentials")
    public ResponseEntity<User> loginUser(
            @RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        return userService.loginUser(username, password);
    }

    @GetMapping("/{username}")
    @Operation(summary = "Get user information", description = "Get detailed information of a user by username")
    public ResponseEntity<User> getUserInfo(
            @PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping("/{username}")
    @Operation(summary = "Update user information", description = "Update the information of a user with the provided username")
    public ResponseEntity<String> updateUserInfo(
            @PathVariable("username") String username,
            @RequestBody User user) {
        return userService.updateUser(username, user);
    }


    // 其他API方法
}
