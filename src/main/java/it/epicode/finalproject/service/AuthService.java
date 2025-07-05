package it.epicode.finalproject.service;

import it.epicode.finalproject.dto.LoginDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.UserRepository;
import it.epicode.finalproject.security.JwtTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTool jwtTool;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public String login(LoginDto loginDto) throws NotFoundException {
        System.out.println("Tentativo login per: " + loginDto.getEmail()); // 👈 LOG DI DEBUG

        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new NotFoundException("User with this email/password not found"));

        if (passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            System.out.println("Login riuscito, generazione token..."); // 👈 LOG OK
            return jwtTool.createToken(user);
        } else {
            System.out.println("Password errata per: " + loginDto.getEmail()); // 👈 LOG ERRORE
            throw new NotFoundException("Invalid email or password");
        }
    }


}