package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.TimeSlot;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ReservationDto {
    @NotNull(message = "La data della prenotazione è obbligatoria")
    private LocalDate date;

    @NotNull(message = "La fascia oraria è obbligatoria")
    private TimeSlot timeSlot;

    @NotNull(message = "L'ID dell'utente è obbligatorio")
    private Integer userId;

    @NotNull(message = "L'ID della tessera digitale è obbligatorio")
    private Integer digitalCardId;
}