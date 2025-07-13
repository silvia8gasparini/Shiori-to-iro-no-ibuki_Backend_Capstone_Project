package it.epicode.finalproject.controller;

import it.epicode.finalproject.dto.DigitalCardDto;
import it.epicode.finalproject.dto.EmailDto;
import it.epicode.finalproject.dto.UserDto;
import it.epicode.finalproject.dto.UserProfileDto;
import it.epicode.finalproject.exception.NotFoundException;
import it.epicode.finalproject.exception.ValidationException;
import it.epicode.finalproject.model.User;
import it.epicode.finalproject.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUserByAdmin(@RequestBody @Valid UserDto userDto, BindingResult bindingResult)
            throws ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + s2));
        }
        return userService.createUserByAdmin(userDto);
    }

    @GetMapping("")
    @PreAuthorize("hasAuthority('ADMIN')")
    public Page<User> getAllUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {
        return userService.getAllUsers(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User getUser(@PathVariable int id) throws NotFoundException {
        return userService.getUser(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User updateUser(@PathVariable int id, @RequestBody @Valid UserDto userDto, BindingResult bindingResult)
            throws NotFoundException, ValidationException {
        if (bindingResult.hasErrors()) {
            throw new ValidationException(bindingResult.getAllErrors().stream()
                    .map(err -> err.getDefaultMessage())
                    .reduce("", (s1, s2) -> s1 + s2));
        }
        return userService.updateUser(id, userDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable int id) throws NotFoundException {
        userService.deleteUser(id);
    }

    @PatchMapping("/{id}/avatar")
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public String patchAvatar(@PathVariable int id, @RequestParam("file") MultipartFile file)
            throws NotFoundException, IOException {
        return userService.patchUser(id, file);
    }

    @PostMapping("/admin/send-mail")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> sendMail(@RequestBody @Valid EmailDto emailDto, Authentication authentication) {
        String from = authentication.getName();

        return ResponseEntity.ok("Email inviata con successo.");
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_USER') or hasAuthority('ROLE_ADMIN')")
    public UserProfileDto getCurrentUser(@AuthenticationPrincipal User user) {
        UserProfileDto userProfileDto = new UserProfileDto();
        userProfileDto.setId(user.getId());
        userProfileDto.setName(user.getName());
        userProfileDto.setSurname(user.getSurname());
        userProfileDto.setEmail(user.getEmail());
        userProfileDto.setAvatarUrl(user.getAvatarUrl());

        if (user.getDigitalCard() != null) {
            DigitalCardDto cardDto = new DigitalCardDto();
            cardDto.setId(user.getDigitalCard().getId());
            cardDto.setCardNumber(user.getDigitalCard().getCardNumber());
            cardDto.setIssuedAt(user.getDigitalCard().getIssuedAt());
            cardDto.setUserId(user.getId());

            userProfileDto.setDigitalCard(cardDto);
        }

        return userProfileDto;
    }

}
