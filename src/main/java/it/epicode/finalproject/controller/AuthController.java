package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.UserRegistrationDto;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.AuthService;
import it.epicode.finalproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegistrationDto userRegistrationDto) {
        User created = userService.saveUser(userRegistrationDto);
        return ResponseEntity.ok(created);
    }
}
