package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MicroSeasonDto {
    @NotEmpty(message = "Il nome giapponese è obbligatorio")
    private String japaneseName;

    @NotEmpty(message = "Il nome italiano è obbligatorio")
    private String italianName;

    @NotNull(message = "La data di inizio è obbligatoria")
    private LocalDate startDate;

    @NotNull(message = "La data di fine è obbligatoria")
    private LocalDate endDate;

    private String displayPeriod;
}
