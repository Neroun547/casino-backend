package org.example.casino.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @GetMapping("/api/users/")
    public String users() {
        return "Users";
    }
}
