package org.example.casino.controllers;

import jakarta.validation.Valid;
import org.apache.coyote.BadRequestException;
import org.example.casino.dto.AuthRequest;
import org.example.casino.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    AuthService authService;

    @PostMapping("/api/auth/")
    ResponseEntity<String> auth(@Valid @RequestBody AuthRequest authRequest) {
        try {
            String token = authService.auth(authRequest.getUsernameOrEmail(), authRequest.getPassword());

            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch(BadRequestException ex) {
            return new ResponseEntity<String>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
