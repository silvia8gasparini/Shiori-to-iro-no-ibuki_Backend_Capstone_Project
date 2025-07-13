package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {
    private int id;
    @NotEmpty(message = "Il nome è obbligatorio")
    private String name;

    @NotEmpty(message = "Il cognome è obbligatorio")
    private String surname;

    @NotEmpty(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotEmpty(message = "Password is required")
    private String password;

    private String avatarUrl;

    private DigitalCardDto digitalCard;

    @NotNull(message = "Role is required")
    private Role role;
}
