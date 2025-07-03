package it.epicode.finalproject.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemDto {
    @NotNull(message = "L'ID è obbligatorio")
    private Integer bookId;

    @Min(value = 1, message = "Inserire almeno 1 elemento")
    private int quantity;

    @NotNull(message = "È obbligatorio indicare il prezzo al momento della selezione")
    private Double priceAtSelection;

}
