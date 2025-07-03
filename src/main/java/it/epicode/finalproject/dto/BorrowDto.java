package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;

@Data
public class BorrowDto {
    @NotNull(message = "La data di inizio prestito è obbligatoria")
    private LocalDate startDate;

    @NotNull(message = "La data limite per la restituzione è obbligatoria")
    private LocalDate dueDate;

    private LocalDate returnDate;

    @NotNull(message = "L'ID del libro è obbligatorio")
    private Integer bookId;

    @NotNull(message = "L'ID dell'utente è obbligatorio")
    private Integer userId;

    @NotNull(message = "L'ID della tessera digitale è obbligatorio")
    private Integer digitalCardId;
}
