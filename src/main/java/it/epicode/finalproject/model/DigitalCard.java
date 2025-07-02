package it.epicode.finalproject.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class DigitalCard {
    @Id
    @GeneratedValue
    private int id;

    private String cardNumber;
    private LocalDate issuedAt;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "digitalCard")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "digitalCard")
    private List<Borrow> borrows;
}
