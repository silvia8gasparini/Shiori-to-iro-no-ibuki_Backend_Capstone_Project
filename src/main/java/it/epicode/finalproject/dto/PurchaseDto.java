package it.epicode.finalproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
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
