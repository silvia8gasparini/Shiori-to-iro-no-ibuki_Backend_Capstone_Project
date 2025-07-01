package it.epicode.finalproject.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Borrow {
    @Id
    @GeneratedValue
    private int id;
    private LocalDate startDate;
    private LocalDate dueDate;
    private LocalDate returnDate;

    private boolean returned = false;

    @ManyToOne
    private Book book;

    @ManyToOne
    private User user;

    @ManyToOne
    private DigitalCard digitalCard;
}
