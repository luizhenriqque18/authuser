package com.ead.authuser.controllers;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers(){
        var users = this.userService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getOneUser(@PathVariable(value = "userId") UUID userId){
        var user = this.userService.findById(userId);

        if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(user);
        }
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable(value = "userId") UUID userId){
        var user = this.userService.findById(userId);

        if(!user.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        } else {
            this.userService.delete(user.get());

            return ResponseEntity.status(HttpStatus.OK).body("User deleted successfully.");
        }
    }
}
