package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.LoginDto;
import it.epicode.finalproject.dto.UserRegistrationDto;
import it.epicode.finalproject.enumeration.Role;
import it.epicode.finalproject.exception.UnAuthorizedException;
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
       if (userRegistrationDto.getRole() == Role.ADMIN) {
          throw new UnAuthorizedException("Non puoi registrarti come ADMIN!");
       }

        User created = userService.saveUser(userRegistrationDto);
        return ResponseEntity.ok(created);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {
        System.out.println("Tentativo login: " + loginDto.getEmail()); // Debug utile
        String token = authService.login(loginDto);
        return ResponseEntity.ok(token);
    }

}
