package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserRegistrationDto {
    @NotEmpty(message = "Il nome è obbligatorio")
    private String name;

    @NotEmpty(message = "Il cognome è obbligatorio")
    private String surname;

    @NotEmpty(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotEmpty(message = "La password è obbligatoria")
    private String password;

    private Role role;
}

