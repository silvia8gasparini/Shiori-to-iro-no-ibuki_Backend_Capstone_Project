package it.epicode.finalproject.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class EmailDto {
    @NotEmpty(message = "Il destinatario è obbligatorio")
    @Email(message = "Formato email non valido")
    private String to;

    @NotEmpty(message = "L'oggetto è obbligatorio")
    private String subject;

    @NotEmpty(message = "Il corpo del messaggio è obbligatorio")
    private String body;
}
