package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SeasonalColorDto {
    @NotEmpty(message = "Il nome giapponese è obbligatorio")
    private String japaneseName;

    @NotEmpty(message = "Il nome italiano è obbligatorio")
    private String italianName;

    @NotEmpty(message = "L'RGB è obbligatorio")
    private String rgb;

    @NotEmpty(message = "La descrizione è obbligatorio")
    private String description;

    @NotEmpty(message = "I dettagli sono obbligatori")
    private String details;

    @NotEmpty(message = "L'URL dell'immagine è obbligatorio")
    private String imageUrl;

    @NotNull(message = "L'ID della micro-stagione è obbligatorio")
    private Integer microSeasonId;
}
