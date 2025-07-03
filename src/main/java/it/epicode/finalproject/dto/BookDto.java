package it.epicode.finalproject.dto;

import it.epicode.finalproject.enumeration.Availability;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDto {
    @NotEmpty(message = "Il titolo è obbligatorio")
    private String title;

    @NotEmpty(message = "L'autore è obbligatorio")
    private String author;

    @NotEmpty(message = "L'anno è obbligatorio")
    private String year;

    @NotEmpty(message = "La casa editrice è obbligatoria")
    private String publisher;

    @NotEmpty(message = "L'ISBN è obbligatorio")
    private String isbn;

    @NotNull(message = "Il prezzo è obbligatorio")
    private Double price;

    @NotEmpty(message = "L'URL dell'immagine è obbligatorio")
    private String imageUrl;

    @NotEmpty(message = "La descrizione è obbligatoria")
    private String description;

    @NotNull(message = "La disponibilità è obbligatoria")
    private Availability availability;
}
