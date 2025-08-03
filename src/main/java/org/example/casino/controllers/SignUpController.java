package org.example.casino.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.example.casino.dto.SignUpRequest;
import org.example.casino.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    @Autowired
    UserService userService;

    @PostMapping("/api/signup/")
    public ResponseEntity<String> signup(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userService.createUser(signUpRequest);
        } catch(BadRequestException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<String>("User created successfully", HttpStatus.CREATED);
    }
}
