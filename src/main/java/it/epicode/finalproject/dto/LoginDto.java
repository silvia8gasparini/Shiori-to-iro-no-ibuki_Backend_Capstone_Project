package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class LoginDto {
    @NotEmpty(message = "L'email è obbligatoria")
    private String email;

    @NotEmpty(message = "La password è obbligatoria")
    private String password;
}
