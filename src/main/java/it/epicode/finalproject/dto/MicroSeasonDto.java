package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotEmpty;

public class MicroSeasonDto {
    @NotEmpty(message = "Il nome giapponese è obbligatorio")
    private String japaneseName;

    @NotEmpty(message = "Il nome italiano è obbligatorio")
    private String italianName;
}
