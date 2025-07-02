package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterDto {
    @NotEmpty(message = "Il nome è obbligatorio")
    private String name;

    @NotEmpty(message = "Il cognome è obbligatorio")
    private String surname;

    @NotEmpty(message = "L'email è obbligatoria")
    @Email(message = "Formato email non valido")
    private String email;

    @NotEmpty(message = "La password è obbligatoria")
    private String password;

    private String avatarUrl;

    @NotNull(message = "Il ruolo è obbligatorio")
    private Role role;
}