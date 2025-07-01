package it.epicode.finalproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Purchase {
    @Id
    @GeneratedValue
    private int id;
    private LocalDateTime dataAcquisto;
    private double total;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;
}
