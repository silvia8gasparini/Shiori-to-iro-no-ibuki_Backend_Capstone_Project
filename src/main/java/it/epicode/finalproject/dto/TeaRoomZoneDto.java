package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class TeaRoomZoneDto {
    @NotEmpty(message = "Il nome della zona è obbligatorio")
    private String name;
    @NotEmpty(message = "La descrizione della zona è obbligatoria")
    private String description;
    @NotEmpty(message = "L'URL dell'immagine è obbligatorio")
    private String imageUrl;
}
