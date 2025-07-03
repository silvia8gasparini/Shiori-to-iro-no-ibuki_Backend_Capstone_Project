package it.epicode.finalproject.service;

import com.cloudinary.Cloudinary;
import it.epicode.finalproject.dto.UserDto;
import it.epicode.finalproject.dto.UserRegistrationDto;
import it.epicode.finalproject.enumeration.Role;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Cloudinary cloudinary;

    @Autowired
    private EmailService mailService;

    public User saveUser(UserRegistrationDto userRegistrationDto) {
        User user = new User();
        user.setName(userRegistrationDto.getName());
        user.setSurname(userRegistrationDto.getSurname());
        user.setEmail(userRegistrationDto.getEmail());
        user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
        user.setRole(Role.USER); // il ruolo lo assegni tu
        return userRepository.save(user);
    }

    public User createUserByAdmin(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAvatarUrl(userDto.getAvatarUrl());
        user.setRole(userDto.getRole());
        return userRepository.save(user);
    }

    public Page<User> getAllUsers(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return userRepository.findAll(pageable);
    }

    public User getUser(int id) throws NotFoundException {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with ID " + id + " not found"));
    }

    public User updateUser(int id, UserDto userDto) throws NotFoundException {
        User userToUpdate = getUser(id);

        userToUpdate.setName(userDto.getName());
        userToUpdate.setSurname(userDto.getSurname());
        userToUpdate.setEmail(userDto.getEmail());

        if (!passwordEncoder.matches(userDto.getPassword(), userToUpdate.getPassword())) {
            userToUpdate.setPassword(passwordEncoder.encode(userDto.getPassword()));
        }

        return userRepository.save(userToUpdate);
    }

    public String patchUser(int id, MultipartFile file) throws NotFoundException, IOException {
        User userToPatch = getUser(id);
        String avatarUrl = (String) cloudinary.uploader()
                .upload(file.getBytes(), Collections.emptyMap())
                .get("url");

        userToPatch.setAvatarUrl(avatarUrl);
        userRepository.save(userToPatch);
        return avatarUrl;
    }

    public void deleteUser(int id) throws NotFoundException {
        User userToDelete = getUser(id);
        userRepository.delete(userToDelete);
    }

    public void send(String from, String to, String subject, String body) {
        mailService.send(from, to, subject, body);
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

}
