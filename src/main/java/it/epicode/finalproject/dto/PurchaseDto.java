package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PurchaseDto {
    @NotNull(message = "La data d'acquisto è obbligatoria")
    private LocalDateTime purchaseDate;

    @NotNull(message = "Il totale è obbligatorio")
    private Double total;

    @NotNull(message = "L'ID del libro è obbligatorio")
    private Integer bookId;

    @NotNull(message = "L'ID dell'utente è obbligatorio")
    private Integer userId;
}
